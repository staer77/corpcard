const $cmmn = (function () {
  'use strict';

  /**
   * null이면 true 반환
   * @param {String} checkValue : value
   */
  const isNullorEmpty = function (checkValue) {
    //null check
    if (checkValue == null || typeof checkValue === 'undefined') {
      return true;
    }
    //빈 문자열값 체크
    if (typeof checkValue === 'string' && checkValue === '') {
      return true;
    }
    return false;
  }

/**
 * form 정보를 json 형식으로 변환
 * @param {String} formId : formId
 */
  const serializeObject = function (formId) {
    const form = $(`#${formId}`);
    let obj = null;

    if (form[0].tagName && form[0].tagName.toUpperCase() == "FORM") {
      const arr = form.serializeArray();
      if (arr) {
        obj = {};
        $.each(arr, function () {
          if (this.value === "") {
            return true;
          } else {
            obj[this.name] = this.value;
          }
        });
      }
    }
    return obj;
  }

  /**
   * 쉼표 삭제
   * @param {String} value : input value
   */
  const uncomma = function (value) {
    const str = new String(value);
    return str.replace(/,/g, "");
  }

  /**
   * 문자 삭제 및 쉼표 추가
   * @param {String} value : input value
   */
  const convertToCurrency = function (obj) {
    let answer = new String(obj);
    let err;

    if (!isNaN(answer.split(",").join(""))) {
      if (answer.includes('.')) {
        err = String(obj).split('.')[0];
        answer = err.toString().split(",").join("").replace(/\B(?=(\d{3})+(?!\d))/g, ',');
        answer = answer.concat("." + String(obj).split('.')[1]);
      } else {
        answer = answer.toString().split(",").join("").replace(/\B(?=(\d{3})+(?!\d))/g, ',');
      }
    }
    return answer;
  }

  /**
  * Date format 설정
  * @param {timestamp} timestamp : timestamp
  * @param {String} type : date format
  */
  const formatDate = function (timestamp, type) {
    let date;
    isNullorEmpty(timestamp) ? date = new Date : date = new Date(timestamp);
    const year = date.getFullYear();
    const month = date.getMonth() + 1;
    const days = date.getDate();
    let result;

    switch (type) {
      case 'YYYY-mm':
        result = `${year}-${month < 10 ? `0${month}` : month}`;
        break;
      case 'mm.dd':
        result = `${month < 10 ? `0${month}` : month}.${days < 10 ? `0${days}` : days}`;
        break;
      default:
        result = `${year}-${month < 10 ? `0${month}` : month}-${days < 10 ? `0${days}` : days}`;
        break;
    }

    return result;
  }


  /**
   * navbar에 사용자 정보 출력
   */
  const paintUserInfo = function () {
    $.ajax({
      type: "GET",
      url: "/common/userInfo",
      dataType: "json",
      success: function (data) {
        if (data.CODE === "SUCCESS") {
          const result = data.result;
          $("#navUserInfo").text(`${result.userNm}`);
        } else if (data.CODE === "ERR") {
          return alert("사용자 정보 조회에 실패했습니다. 관리자에게 문의해주시기 바랍니다.");
        }
      },
      error: function () {
        return alert("사용자 정보 조회에 실패했습니다. 관리자에게 문의해주시기 바랍니다.");
      }
    });
  }


  return {
    isNullorEmpty: isNullorEmpty,          //null이면 true 반환
    serializeObject: serializeObject,      //form 정보를 json 형식으로 변환
    uncomma: uncomma,                      //쉼표 삭제
    convertToCurrency: convertToCurrency,  //문자 삭제 및 쉼표 추가
    formatDate: formatDate,                //Date format 설정
    paintUserInfo: paintUserInfo,          //navbar에 사용자 정보 출력
  }
}());