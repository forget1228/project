import { Injectable, Injector } from '@angular/core';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpHeaders, HttpResponse, HttpErrorResponse, HttpClient
} from '@angular/common/http';

import { mergeMap, catchError } from 'rxjs/operators';
import { Observable, of } from 'rxjs/index';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd';
import {PublicFun} from "./public.fun";
import { environment } from '@env/environment';

/** Pass untouched request through to the next request handler. */
@Injectable()
export class PublicInterceptor implements HttpInterceptor {
  constructor(private injector: Injector){}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<any> {
    let originString =  PublicFun.IP_AND_PORT;
    let secureReq = null;
    if(req.url.indexOf('addProject')>=0){
      secureReq = req.clone({
        url: originString+req.url,
      });
    }else{
      secureReq = req.clone({
        url: originString+req.url,
        headers: new HttpHeaders({ 'Content-Type': 'application/json'})
      });
    }
    // send the cloned, 'secure' request to the next handler.
    return next.handle(secureReq).pipe(
      mergeMap((event: any) => {
        // 允许统一对请求错误处理，这是因为一个请求若是业务上错误的情况下其HTTP请求的状态是200的情况下需要
        if (event instanceof HttpResponse && event.status === 200)
          return this.handleData(event);
        // 若一切都正常，则后续操作
        return of(event);
      }),
      catchError((err: HttpErrorResponse) => this.handleData(err)),
    );
  }

  private handleData(
    event: HttpResponse<any> | HttpErrorResponse,
  ): Observable<any> {
    // 业务处理：一些通用操作
    switch (event.status) {
      case 200:
        // 业务层级错误处理，以下是假定restful有一套统一输出格式（指不管成功与否都有相应的数据格式）情况下进行处理
        // 例如响应内容：
        //  错误内容：{ status: 1, msg: '非法参数' }
        //  正确内容：{ status: 0, response: {  } }
        // 则以下代码片断可直接适用
        // if (event instanceof HttpResponse) {
        //     const body: any = event.body;
        //     if (body && body.status !== 0) {
        //         this.msg.error(body.msg);
        //         // 继续抛出错误中断后续所有 Pipe、subscribe 操作，因此：
        //         // this.http.get('/').subscribe() 并不会触发
        //         return throwError({});
        //     } else {
        //         // 重新修改 `body` 内容为 `response` 内容，对于绝大多数场景已经无须再关心业务状态码
        //         return of(new HttpResponse(Object.assign(event, { body: body.response })));
        //         // 或者依然保持完整的格式
        //         return of(event);
        //     }
        // }
        break;
      case 401: // 未登录状态码
        this.goTo('/passport/login');
        break;
      case 403:
      case 404:
      case 500:
        this.goTo(`/${event.status}`);
        break;
      default:
        if (event instanceof HttpErrorResponse) {
          console.warn(
            '未可知错误，大部分是由于后端不支持CORS或无效配置引起',
            event,
          );
          this.msg.error(event.message);
        }
        break;
    }
    return of(event);
  }

  private goTo(url: string) {
    setTimeout(() => this.injector.get(Router).navigateByUrl(url));
  }

  get msg(): NzMessageService {
    return this.injector.get(NzMessageService);
  }
}
