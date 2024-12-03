const $insert = (function () {
	'use strict';
	const userId = document.querySelector('#userId').value;   // 사용자 ID
	
	/* init */
	const init = function () {
		
	}
	const insert = function(){
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
	       		roomNm: $('#roomNm').val(),
	       		roomType: $('#roomType').val(),
	        	rentType: $('#rentType').val(),
	        	deposit: $('#deposit').val(),
	        	rentPrice: $('#rentPrice').val(),
				regId: userId
	    	};
			$.ajax({
				type: "POST",
		      	url: "/room/insert",
		      	data: data,
		      	dataType: "json",
		      	success: function (data) {
					if(data.result != null && data.result != ""){
						let obj = data.result;
						if(data.CODE = 'SUCCESS'){
							alert("방 등록을 완료하였습니다.");
							location.href = "/main";
						}
						else{
							alert("방 등록에 실패했습니다.");
						}
					}
		      	},
		      	error: function () {
		        	return alert("방 등록에 실패했습니다.");
		      	}
			});
		}		
	}
	
	const mainPage = function(){
		location.href = "/main";
	}
	
	return {
		init: init,
		insert: insert,
		mainPage: mainPage,
	}
}());
