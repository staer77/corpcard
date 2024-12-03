const $main = (function () {
	'use strict';
	const userId = document.querySelector('#userId').value;   // 사용자 ID
	
	/* init */
	const init = function () {
		selectRoomList();
		updatePage();
	}
	
	const selectRoomList = function(){
		const data = {
       		rentPrice1: $('#rentPrice1').val(),
       		rentPrice2: $('#rentPrice2').val(),
        	rentType: $('#rentType').val(),
        	roomType: $('#roomType').val(),
    	};
		if(data.rentPrice1 == null || data.rentPrice1 == ''){
			data.rentPrice1 = -1;
		}
		if(data.rentPrice2 == null || data.rentPrice2 == ''){
			data.rentPrice2 = -1;
		}
		$.ajax({
			type: "GET",
	      	url: "/room/list",
	      	data: data,
	      	dataType: "json",
	      	success: function (data) {
				if(data.result != null && data.result != ""){
					let obj = data.result;
					for(let i=0;i<obj.length;i++){
						$('#roomTbody').append(
							"<tr>" + 
			                "<td>"+ obj[i].seq +"</td>" +
							"<td>"+ obj[i].roomNm +"</td>" +
							"<td>"+ obj[i].roomType +"</td>" +
							"<td>"+ obj[i].rentType +"</td>" +
							"<td>"+ obj[i].deposit +"</td>" +
							"<td>"+ obj[i].rentPrice +"</td>" +
	                		"</tr>"
						);
					}
				}
	      	},
	      	error: function () {
	        	return alert("방 내역 조회에 실패했습니다.");
	      	}
		});
	}
	
	const fnSerach = function(){
		if($('#rentPrice1').val() > $('#rentPrice2').val()){
			return alert('최소금액이 최대금액보다 클 수 없습니다.');
		}
		else{
			$('#roomTbody').empty();
			selectRoomList();
		}		
	}
	
	const insertPage = function(){
		location.href = "/main/insert";
	}
	
	const updatePage = function(){
		$("#table").on('dblclick', 'tbody tr', function () {
		    //var row = $("#table").DataTable().row($(this)).data();
			let td = $(this).children();
			let seq = td.eq(0).text();
			location.href = "/main/update?page=update&seq="+seq;
		});
	}
	
	const selectMyRoomList = function(){
		$('#roomTbody').empty();
		
		const data = {
			regId: userId
    	};

		$.ajax({
			type: "GET",
	      	url: "/room/mylist",
			data: data,
	      	dataType: "json",
	      	success: function (data) {
				if(data.result != null && data.result != ""){
					let obj = data.result;
					for(let i=0;i<obj.length;i++){
						$('#roomTbody').append(
							"<tr>" + 
			                "<td>"+ obj[i].seq +"</td>" +
							"<td>"+ obj[i].roomNm +"</td>" +
							"<td>"+ obj[i].roomType +"</td>" +
							"<td>"+ obj[i].rentType +"</td>" +
							"<td>"+ obj[i].deposit +"</td>" +
							"<td>"+ obj[i].rentPrice +"</td>" +
	                		"</tr>"
						);
					}
				}
	      	},
	      	error: function () {
	        	return alert("방 내역 조회에 실패했습니다.");
	      	}
		});
	}
		
	return {
		init: init,
		selectRoomList: selectRoomList,
		fnSerach: fnSerach,
		insertPage: insertPage,
		selectMyRoomList: selectMyRoomList
	}
}());
