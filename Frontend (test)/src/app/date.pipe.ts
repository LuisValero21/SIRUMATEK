import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'date'
})
export class DatePipe implements PipeTransform {

  transform(value: any, format: string = 'shortDate'): any {
    // Puedes usar una librería como date-fns o moment.js para formatear la fecha
    const options: Intl.DateTimeFormatOptions = { year: 'numeric', month: '2-digit', day: '2-digit' };
    if (format === 'shortDate') {
      return new Intl.DateTimeFormat('es-ES', options).format(new Date(value));
    }
    // Añadir otros formatos si es necesario
    return value;
  }
}
