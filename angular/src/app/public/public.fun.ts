import {environment} from "@env/environment";

export class PublicFun {

  static get IP_AND_PORT(){
    let originString = '';
    if(window.location.origin.indexOf('localhost:4200')>=0){
      originString = window.location.protocol +'//localhost:8080/';
    }else{
      originString = window.location.origin + '/';
    }
    return originString+"bbg/";
  }

  static today = new Date();
  constructor(){}
  static get Today():Date{
    return PublicFun.today;
  }

  //时间格式化方法
  //  let retTime = this.mms.dateFormat(new Date(),'yyyy-MM-dd');  // '2017-01-11'
  static dateFormat(inData?: Date, format?: string):string{
    if (inData == undefined || (inData + '') == '' || (inData + '') == 'null') {
      return null;
    }
    let o = {
      'M+': inData.getMonth() + 1,                 //月份
      'd+': inData.getDate(),                    //日
      'h+': inData.getHours(),                   //小时
      'm+': inData.getMinutes(),                 //分
      's+': inData.getSeconds(),                 //秒
      'q+': Math.floor((inData.getMonth() + 3) / 3), //季度
      'S': inData.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(format)) {
      format = format.replace(RegExp.$1, (inData.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (let k in o) {
      if (new RegExp('(' + k + ')').test(format)) {
        format = format.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)));
      }
    }
    return format;
  }
}
