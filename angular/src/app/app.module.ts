import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgZorroAntdModule, NZ_I18N, zh_CN } from 'ng-zorro-antd';

import { AppComponent } from './app.component';

/** 配置 angular i18n **/
import { registerLocaleData } from '@angular/common';
import zh from '@angular/common/locales/zh';
registerLocaleData(zh);


import { ParamsService } from './public/params.service';
import { httpInterceptorProviders } from './public';
import { HttpService } from './public/http.service';

import { ReportComponent } from './report/report.component';
import { HeaderComponent } from './report/header/header.component';
import { ContentComponent } from './report/content/content.component';



const reportComponents = [
  ReportComponent,
  HeaderComponent,
  ContentComponent
];
const reportSeivices = [
  httpInterceptorProviders,
  ParamsService,
  HttpService
];


@NgModule({
  declarations: [
    AppComponent,
    ...reportComponents
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    /** 导入 ng-zorro-antd 模块 **/
    NgZorroAntdModule
  ],
  bootstrap: [ AppComponent ],
  /** 配置 ng-zorro-antd 国际化 **/
  providers   : [
    { provide: NZ_I18N, useValue: zh_CN },
    ...reportSeivices
  ]
})
export class AppModule { }
