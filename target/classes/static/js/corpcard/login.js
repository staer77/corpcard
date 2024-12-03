const $login = (function () {
    'use strict'

	var idChk = false;

    /**
     * init
     */
    const init = function () {
        saveId();
    }

    /**
     * 쿠키 저장
     * @param {String} cookieName : 쿠키 key
     * @param {String} value : 사용자 ID
     * @param {Number} exdays : 만료일
     */
    function setCookie(cookieName, value, exdays) {
        const exdate = new Date();

        exdate.setDate(exdate.getDate() + exdays);
        const cookieValue = escape(value) + ((exdays == null) ? "" : "; expires=" + exdate.toUTCString());
        document.cookie = cookieName + "=" + cookieValue;
    }

    /**
     * 쿠키 삭제
     * @param {String} cookieName : 쿠키 key
     */
    function deleteCookie(cookieName) {
        const expireDate = new Date();

        expireDate.setDate(expireDate.getDate() - 1);
        document.cookie = cookieName + "= " + "; expires=" + expireDate.toUTCString();
    }

    /**
     * 쿠키 가져오기
     * @param {String} cookieName : 쿠키 key
     */
    function getCookie(cookieName) {
        cookieName = cookieName + '=';

        const cookieData = document.cookie;
        let start = Number(cookieData.indexOf(cookieName));
        let cookieValue = '';

        if (start !== -1) {
            start += cookieName.length;
            let end = Number(cookieData.indexOf(';', start));
            if (end === -1) end = cookieData.length;
            cookieValue = cookieData.substring(start, end);
        }

        return unescape(cookieValue);
    }

    /**
     * ID 저장
     */
    const saveId = function () {
        const key = getCookie("key");
        const userId = $("#userId");
        const checkId = $("#checkId");

        userId.val(key);

        userId.val() !== "" && checkId.attr("checked", true);

        checkId.change(function () {
            checkId.is(":checked") ? setCookie("key", userId.val(), 7) : deleteCookie("key");
        });

        userId.keyup(function () {
            $("#checkId").is(":checked") && setCookie("key", userId.val(), 7);
        });
    }

	const login = function(){
		const data = {
		       		userId: $('#userId').val(),
					userPw: $('#userPw').val()
		    	};
				
				$.ajax({
					type: "POST",
			      	url: "/login",
					data: data,
			      	dataType: "json",
			      	success: function (data) {
						if(data.CODE == 'SUCCESS'){
							location.href = '/main';
						}
			      	},
			      	error: function () {
			        	return alert("회원가입에 실패했습니다.");
			      	}
				});
	}

	const sign = function(){
		if($('#signNm').val() == null || $('#signNm').val() == ''){
			return alert("이름을 입력해주세요");
		}
		else if($('#signId').val() == null || $('#signId').val() == ''){
			return alert("아이디를 입력해주세요");
		}
		else if($('#signPw').val() == null || $('#signPw').val() == ''){
			return alert("비밀번호를 입력해주세요");
		}
		else if($('#signPhoneNum').val() == null || $('#signPhoneNum').val() == ''){
			return alert("휴대폰번호를 입력해주세요");
		}
		else{
			if(idChk == true && $('#checkPwSpan').html() != '' && $('#checkPwSpan').html() != '불일치'){
				const data = {
		       		userNm: $('#signNm').val(),
		       		userId: $('#signId').val(),
		        	userPw: $('#checkPw').val(),
		        	phoneNum: $('#signPhoneNum').val(),
		    	};
				
				$.ajax({
					type: "POST",
			      	url: "/login/insert",
					data: data,
			      	dataType: "json",
			      	success: function (data) {
						if(data.CODE == 'SUCCESS'){
							alert("회원가입에 성공하였습니다.");
							location.reload();
						}
			      	},
			      	error: function () {
			        	return alert("회원가입에 실패했습니다.");
			      	}
				});
			}
			else{
				if(!idChk){
					return alert("아이디를 다시 입력해주세요");
				}
				if($('#checkPwSpan').html() == '' || $('#checkPwSpan').html() == '불일치'){
					return alert("비밀번호를 다시 입력해주세요");
				}
			}
		}
	}
	
	const chkId = function(){
		const data = {
	       	userId: $('#signId').val()
	    };
		
		$.ajax({
				type: "GET",
		      	url: "/login/info",
				data: data,
		      	dataType: "json",
		      	success: function (data) {
					if(data.CODE == 'SUCCESS'){
						alert("이미 사용중인 아이디 입니다.");
						idChk = false;
					}
					else{						
						alert("사용가능한 아이디 입니다.");
						idChk = true;
					}
		      	},
		      	error: function () {
		        	return alert("조회에 실패했습니다.");
		      	}
			});
	}
	
	const chkPw = function(){
		if($('#signPw').val() != $('#checkPw').val()){
			$('#checkPwSpan').html('불일치');
			$('#checkPwSpan').css('color', 'red');
		}
		else{
			$('#checkPwSpan').html('일치');
			$('#checkPwSpan').css('color', 'blue');
		}
	}

    return {
        init: init,
		sign: sign,
		chkPw: chkPw,
		chkId: chkId,
		login: login,
    }
}());