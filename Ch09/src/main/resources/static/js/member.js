/**
 * 
 */
$(document).ready(function(){
	//member 목록 요청
	$('.member_list1').click(function(){
		$.ajax({
			'url': '/Ch09/member',
			'method': 'GET',
			'dataType': 'json',
			'success': function(data){
				console.log(data);
			}
		});
	});
	
	//member 목록 요청
	$('.member_list2').click(function(){
		$.ajax({
			'url': '/Ch09/member/r101',
			'method': 'GET',
			'dataType': 'json',
			'success': function(data){
				console.log(data);
			}
		});
	});
	
	//member 등록 요청
	$('.member_register').click(function(){
		
		let jsonData = {
				"uid": "r101",
				"name": "홍길동",
				"hp": "010-1234-1111",
				"pos": "차장"
		}
		
		
		$.ajax({
			url: '/Ch09/member',
			method: 'POST',
			data: jsonData,
			dataType: 'json',
			success: function(data){
				console.log(data);
			}
		});
	});
	
	//member 수정 요청
	$('.member_modify').click(function(){
		let jsonData = {
				"uid": "r101",
				"name": "홍길동",
				"hp": "010-1234-2222"
		}
		
		
		$.ajax({
			url: '/Ch09/member',
			method: 'PUT',
			data: jsonData,
			dataType: 'json',
			success: function(data){
				console.log(data);
			}
		});
	});
	
	//member 삭제 요청
	$('.member_delete').click(function(){
		
		let jsonData = {"uid": "r101"};
		
		$.ajax({
			url: '/Ch09/member/r101',
			method: 'DELETE',
			data: jsonData,
			dataType: 'json',
			success: function(data){
				console.log(data);
			}
		});
	});
});