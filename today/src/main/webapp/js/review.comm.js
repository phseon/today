$(function(){
	let currentPage;
	let count;
	let rowCount;
	
	//댓글 목록
	function selectList(pageNum){
		currentPage = pageNum;
		
		//로딩 이미지 노출
		$('#loading').show();
		
		$.ajax({
			url:'listComm.do',
			type:'post',
			data:{pageNum:pageNum, r_num:$('#r_num').val()},
			dataType:'json',
			success:function(param){
				//로딩 이미지 감추기
				$('#loading').hide();
				
				count = param.count;
				rowCount = param.rowCount;
				
				if(pageNum == 1){
					//처음 호출시는 목록을 표시하는 div의 내부 내용물 제거
					$('#output').empty();
				}
				
				$(param.list).each(function(index,item){
					let output = '<div class="item">';
					
					/*
					//로그인한 회원번호와 작성자의 회원번호 일치 여부 체크
					if(param.user_num != item.m_num){		
					output += '<h4>' + item.m_num + '번 회원'
							+ ' | ' + item.c_date
							+ '</h4>';
					}else{
					output += '<h4>' + item.m_num + '번 회원'
							+ ' | ' + item.c_date
							+ ' <input type="button" data-cnum="' + item.c_num+'" value="수정" class="modify-btn">'
							+ ' <input type="button" data-cnum="' + item.c_num+'" value="삭제" class="delete-btn">'
							+ '</h4>';
							}
					
					output += '<p>' + item.c_content + '</p>';
					
					let c_count = '<div id="c_count">' + param.count + '</div>';
				
					output += '<hr size="1" class="comm_hr" noshade width="100%">';
				
					output += '</div>';
*/

					output += '<h4>' + item.m_num + '번 회원 | '
							+ item.c_num + '</h4>';
					output += '<div class="sub-item">';
					output += '<p>' + item.c_content + '</p>';
					
					
					//로그인한 회원번호와 작성자의 회원번호 일치 여부 체크
					if(param.user_num == item.m_num){
						//로그인한 회원번호와 작성자 회원번호 일치
						output += ' <input type="button" data-cnum="'+item.c_num+'" value="수정" class="modify-btn">';	
						output += ' <input type="button" data-cnum="'+item.c_num+'" value="삭제" class="delete-btn">';						
					}
					let c_count = '<div id="c_count">' + param.count + '</div>';
				
					output += '<hr size="1" class="review_hr" noshade width="100%">';
					output += '</div>';
					output += '</div>';
					




					//문서 객체에 추가
					$('#output').append(output);
					$('#c_count').append(c_count);
				});//end of each
				
				//page button 처리
				if(currentPage >= Math.ceil(count/rowCount)){
					//다음 페이지가 없음
					$('.paging-button').hide();
				}else{
					//다음 페이지가 존재
					$('.paging-button').show();
				}
			},
			error:function(request,status,error){
		    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		 }
		});
	}
	
	//페이지 처리 이벤트 연결(다음 댓글 보기 버튼 클릭시 데이터 추가)
	$('.paging-button input').click(function(){
		selectList(currentPage + 1);
	});
	
	
	//댓글 등록
	$('#c_form').submit(function(event){
		//기본 이벤트 제거
		event.preventDefault();
		
		console.log(event.c_content);
		console.log($('#c_content').val());
		console.log($('#c_content').val().trim());
		console.log('ss not a string');
		
		
		if($('#c_content').val().trim()==''){
			alert('내용을 입력하세요!');
			$('#c_content').val('').focus();
			return false;
			}
		
		//form 이하의 태그에 입력한 데이터를 모두 읽어 옴
		let form_data = $(this).serialize();
		console.log("form_data : " + form_data);
		
		//댓글 등록
		$.ajax({
			url:'writeComm.do',
			type:'post',
			data:form_data,
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인해야 작성할 수 있습니다.');
				}else if(param.result == 'success'){
					//폼 초기화
					initForm();
					//댓글 작성이 성공하면 새로 삽입한 글을 포함해서
					//첫번째 페이지의 게시글을 다시 호출함
					selectList(1);
					
				}else{
					alert('댓글 등록 오류 발생');
				}
			},
			error:function(request,status,error,form_data){
		    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error+"form_data:"+form_data);
		 }
		});
	
	});
	
	
	

	
	//댓글 작성 폼 초기화
	function initForm(){
		$('textarea').val('');
		$('#c_first .letter-count').text('300/300');
	}
	//textarea에 내용 입력시 글자수 체크
	$(document).on('keyup','textarea',function(){
		//입력한 글자수 구함
		let inputLength = $(this).val().length;
		
		if(inputLength > 300){//300자를 넘어선 경우}
			$(this).val($(this).val().substring(0,300));
		}else{//300자 이하인 경우
			let remain = 300 - inputLength;
			remain += '/300';
			if($(this).attr('id') == 'c_content'){
				//등록폼 글자수
				$('#c_first .letter-count').text(remain);
			}else{
				//수정폼 글자수
				$('#mc_first .letter-count').text(remain);
			}
			
		}
		
	});
	

	
	
	

	
	//댓글 수정 버튼 클릭시 수정폼 노출
	$(document).on('click','.modify-btn',function(){
		//댓글 번호
		let c_num = $(this).attr('data-cnum');
		//댓글 내용
		//parent -> 원래 목록부분 태그 item, sub-item다시넣어서 계층 ㅁ맞나 비교해보기
		let content = $(this).parent().find('p')
		                     .html().replace(/<br>/gi,'\n');
		//댓글 수정폼 U
		let modifyUI = '<form id="mc_form">';
			modifyUI += '<input type="hidden" name="c_num" id="c_num" value="'+c_num+'">';
			modifyUI += '<textarea rows="1" cols="50" name="c_content" id="mc_content" class="comm-content">'+content+'</textarea>';
			modifyUI += '<div id="mc_first"><span class="letter-count">300/300</span></div>';
			modifyUI += '<div id="mc_second" class="align-right">';
			modifyUI += ' <input type="submit" value="수정">';				
			modifyUI += ' <input type="button" value="취소" class="c-reset">';
			modifyUI += '</div>';
			modifyUI += '<hr size="1" class="review_hr" noshade widht="96%">';
			modifyUI += '</form>';				
			
			//이전에 이미 수정하는 댓글이 있을 경우 수정버튼을
			//클릭하면 숨김 sub-item을 환원시키고 수정폼이 초기화됨
			initModifyForm();
			
			//지금 클릭해서 수정하고자 하는 데이터는 감추기
			//수정버튼을 감싸고 있는 div
			$(this).parent().hide();
			
			//수정폼을 수정하고자 하는 데이터가 있는 div에 노출
			$(this).parents('.item').append(modifyUI);
	
			//입력한 글자수 셋팅
			let inputLength = $('#mc_content').val().length;
			let remain = 300 - inputLength;
			remain += '/300';
			
			//문서 객체에 반영
			$('#mc_first .letter-count').text(remain);
			
	});		
	//수정폼에서 취소 버튼 클릭시 수정폼 초기화
	$(document).on('click','.c-reset',function(){
		initModifyForm();
	});
	//댓글 수정폼 초기화
	function initModifyForm(){
		$('.sub-item').show();
		$('#mc_form').remove();
		
	}
	
	
	
	
	
	
	//댓글 수정
	$(document).on('submit','#mc_form',function(event){
		//기본이벤트 제거(function 매개변수에 event 객체 넣어서 전달 가능하게.)
		event.preventDefault();
		if($('#mc_content').val().trim()==''){
			alert('내용을 입력하세요');
			$('#mc_content').val('').focus();
			return false;
		}
		
		//폼에 입력한 데이터 반환
		let form_data = $(this).serialize();
		//서버와 통신
		$.ajax({
			url:'updateComm.do',
			type:'post',
			data:form_data,
			dataType:'json',
			success:function(param){
					
				if(param.result == 'logout'){
					alert('로그인해야 수정할 수 있습니다.');
				}else if(param.result == 'success'){
					$('#mc_form').parent().find('p')
							.html($('#mc_content').val()
							
												  );
		
					//수정폼 삭제 및 초기화
					initModifyForm();
					selectList(1);
				}else if(param.result == 'wrongAccess'){
					alert('타인의 글을 수정할 수 없습니다.');
				}else{
					alert('댓글 수정 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	})
	//댓글 삭제
	$(document).on('click','.delete-btn',function(){
		//댓글 번호
		let c_num = $(this).attr('data-cnum');
		
		//서버와 통신
		$.ajax({
			url:'deleteComm.do',
			type:'post',
			data:{c_num:c_num},
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인해야 삭제할 수 있습니다.');
				}else if(param.result == 'success'){
					alert('댓글이 삭제되었습니다.');
					selectList(1);
				}else if(param.result == 'wrongAccess'){
					alert('타인의 댓글을 삭제할 수 없습니다.');
				}else{
					alert('댓글 삭제 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		})
	});
	
	
	//초기 데이터(목록) 호출
	selectList(1);
});