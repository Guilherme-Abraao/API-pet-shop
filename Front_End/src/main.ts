import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app/app.module';
import { registerLicense } from '@syncfusion/ej2-base';
import { enableProdMode } from '@angular/core';

registerLicense('ORg4AjUWIQA/Gnt2VFhhQlJBfV5AQmBIYVp/TGpJfl96cVxMZVVBJAtUQF1hSn5Vd0xiXXpfcnZSTmlY');

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));

enableProdMode();