import { Component, OnInit } from '@angular/core';
import { PartmasterService } from 'src/services/partmaster.service';

@Component({
  selector: 'app-partmaster',
  templateUrl: './partmaster.component.html',
  styleUrls: ['./partmaster.component.css']
})
export class PartmasterComponent implements OnInit {

  file: File = null;

  constructor(private partMasterService: PartmasterService) { }

  ngOnInit(): void {
  }

  onChange(event) {
    this.file = event.target.files[0];
  }

  onUpload() {
    console.log(this.file, this.file.name);
    this.partMasterService.excelUpload(this.file).subscribe(
        (event: any) => {
            console.log(event)
          }
    );
  }

  downloadTemplate() {
    console.log("download API", this.file.name)
    this.partMasterService.downloadTemplate(this.file.name).subscribe((responseMessage) => {
      let fileDownload = new Blob([responseMessage], { type: 'application/zip' });
      var fileURL = URL.createObjectURL(fileDownload);
      window.open(fileURL);
    })
  }
  
  downloadExcel() {
    console.log("download API", this.file.name)
    this.partMasterService.downloadExcel(this.file.name).subscribe((responseMessage) => {
      let fileDownload = new Blob([responseMessage], { type: 'application/zip' });
      var fileURL = URL.createObjectURL(fileDownload);
      window.open(fileURL);
    })
  }

  logdownload() {
    console.log("download API", this.file.name)
    this.partMasterService.logdownload(this.file.name).subscribe((responseMessage) => {
      let fileDownload = new Blob([responseMessage], { type: 'application/zip' });
      var fileURL = URL.createObjectURL(fileDownload);
      window.open(fileURL);
    })
  }

}
