import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from '@env/environment';

if (environment.production) {
  enableProdMode();
  window.console.log = function(){};
  window.console.info = function(){};
  window.console.warn = function(){};
  // window.console.error = function(){};
}

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.log(err));
