import { bootstrapApplication , BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { importProvidersFrom } from '@angular/core';

import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AppModule } from './app/app.module';

// Cambia platformBrowserDynamic() por bootstrapApplication()
bootstrapApplication(AppComponent, {
  providers: [
    importProvidersFrom(
      BrowserModule,
      AppModule,
      RouterModule.forRoot([]), // Configura correctamente las rutas
      FormsModule,
      CommonModule
    ),
  ],
}).catch((err) => console.error(err));
