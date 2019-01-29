/** Http interceptor providers in outside-in order */
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { PublicInterceptor } from './publicInterceptor';

export const httpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: PublicInterceptor, multi: true },
];

export const monthList = {
  montheng :['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
  monthchinaese :['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
  months :['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12']
};


export const  url={
  'findbydate':'projectmap/findProject',
  'addProject':'projectmap/addProject'
};

export class BaseApiResponseModel {
  code: number;
  msg: string;
  count: number;
  data: any
}
