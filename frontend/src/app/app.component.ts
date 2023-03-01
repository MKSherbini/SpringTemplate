import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ShopControllerService } from './apis/api/shopController.service';
import { ShopDTO } from './apis/model/shopDTO';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'frontend';
  shops: ShopDTO[] = [];
  constructor(private shop: ShopControllerService) { }

  ngOnInit(): void {
    this.shop.configuration.credentials = {
      BearerAuth: "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwicm9sZXMiOlsiQURNSU4iXSwiaXNzIjoic2VsZiIsIm5hbWUiOiJTa3VsbCIsImlkIjoxLCJleHAiOjE2Nzc4NjQwMDEsImlhdCI6MTUxNjIzOTAyMn0.O3W6OY_70h-wiHYXlD58VcwvzgIpXVm0Hh7KIaZ4PhvfNJmvfhlJHMr6qna65plvn6ylT0aTPs_oEduLdNMoXV58bD_o3aB3F5He9BnA-s0uZGWyLU_ko2WphyO1hhC3tVX87F229E4LtUQABNsH3fl64hWeav3z-XjgU3uSF70qqHMtfVQ0e2MhDgzke2cPqXhKZVVpIFestOHJy98xugfv1eY5W3VAuF1UefRXfVRwx-5P6drX7vRTnDMh5W9Nj3A11e_ryWdaqh2Jm0Qa7n6-BW46ACmgAxvkTpHoSn8eXguym-ra6Ob7X3sQV-GViugFWXlbtMPTc5zxER-abQ"
    };
    this.shop.findAll().subscribe((data: ShopDTO[]) => {
      console.log(data);
      this.parseBlob(data).subscribe((data) => {
        console.log(data);
        this.shops = data;
      });
    });
  }

  parseBlob(data: ShopDTO[]): Observable<any> {
    const reader: FileReader = new FileReader();

    const obs = new Observable((observer: any) => {
      reader.onloadend = (e) => {
        observer.next(JSON.parse(reader.result as any));
        observer.complete();
      }
    });
    reader.readAsText(data as any);
    return obs;
  }


}
