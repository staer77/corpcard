const $update = (function () {
	'use strict';
	const userId = document.querySelector('#userId').value;   // 사용자 ID
	let seq;
	
	/* init */
	const init = function () {
		selectRoom();
	}
	
	const selectRoom = function(){
		const data = {
       		seq: $('#seq').val()
    	};

		$.ajax({
			type: "GET",
	      	url: "/room/detail",
	      	data: data,
	      	dataType: "json",
	      	success: function (data) {
				if(data.result != null && data.result != ""){
					let obj = data.result;
					$('#deposit').val(obj.deposit);
					$('#rentPrice').val(obj.rentPrice);
					$('#rentType').val(obj.rentType);
					$('#roomNm').val(obj.roomNm);
					$('#roomType').val(obj.roomType);
					
					if(obj.regId != userId){
						$("#updateBtn").hide();
						$("#deleteBtn").hide();
					}
				}
	      	},
	      	error: function () {
	        	return alert("방 내역 조회에 실패했습니다. 관리자에게 문의해주시기 바랍니다.");
	      	}
		});
	}
	
	const update = function(){
		if($('#roomNm').val() == null || $('#roomNm').val() == ''){
			return alert("방 이름을 입력해주세요");
		}
		else if($('#roomType').val() == null || $('#roomType').val() == ''){
			return alert("방 유형을 선택해주세요");
		}
		else if($('#rentType').val() == null || $('#rentType').val() == ''){
			return alert("거래유형을 선택해주세요");
		}
		else if($('#deposit').val() == null || $('#deposit').val() == ''){
			return alert("보증금을 입력해주세요");
		}
		else if($('#rentPrice').val() == null || $('#rentPrice').val() == ''){
			return alert("세 비용을 입력해주세요");
		}
		else{
			const data = {
				seq: $('#seq').val(),
	       		roomNm: $('#roomNm').val(),
	       		roomType: $('#roomType').val(),
	        	rentType: $('#rentType').val(),
	        	deposit: $('#deposit').val(),
	        	rentPrice: $('#rentPrice').val(),
				udtId: userId
	    	};
			$.ajax({
				type: "POST",
		      	url: "/room/update",
		      	data: data,
		      	dataType: "json",
		      	success: function (data) {
					if(data.result != null && data.result != ""){
						let obj = data.result;
						if(data.CODE = 'SUCCESS'){
							alert("수정한 내용을 저장하였습니다.");
							location.href = "/main";
						}
						else{
							alert("저장에 실패했습니다.");
						}
					}
		      	},
		      	error: function () {
		        	return alert("저장에 실패했습니다.");
		      	}
			});
		}
	}
	
	const deleteInfo = function(){
		const data = {
			seq: $('#seq').val()
		};
		
		$.ajax({
			type: "DELETE",
	      	url: "/room/delete",
	      	data: data,
	      	dataType: "json",
	      	success: function (data) {
				alert("삭제에 성공했습니다.");
				location.href = "/main";
	      	},
	      	error: function () {
	        	return alert("삭제에 실패했습니다.");
	      	}
		});
	}
	
	const mainPage = function(){
		location.href = "/main";
	}
	
	return {
		init: init,
		update: update,
		mainPage: mainPage,
		deleteInfo: deleteInfo
	}
}());
