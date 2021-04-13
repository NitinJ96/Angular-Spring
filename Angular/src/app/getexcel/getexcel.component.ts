import { Component, OnInit } from '@angular/core';
import { UploadfileService } from 'src/services/uploadfile.service';

@Component({
  selector: 'app-getexcel',
  templateUrl: './getexcel.component.html',
  styleUrls: ['./getexcel.component.css']
})
export class GetexcelComponent implements OnInit {

  file: File = null;
  downloadOption: boolean = false;
  failuremessage: boolean = false;
  excelMessage: boolean = false;

  constructor(private fileUploadService: UploadfileService) { }

  ngOnInit(): void {
  }

  // On file Select
  onChange(event) {
    this.file = event.target.files[0];
  }

// OnClick of button Upload
  onUpload() {
      console.log(this.file, this.file.name);
      this.fileUploadService.upload(this.file).subscribe(
          (event: Number) => {
              console.log(event)
              if (event==0) {
                  this.downloadOption = true
              }
              if(event==1){
                this.downloadOption = false;
                this.excelMessage = true;
              }
              if(event==2){
                this.downloadOption = false;
                this.failuremessage = true;
              }
          }
      );
  }

  getFileInNewWindow() {
    console.log("download API", this.file.name)
    this.fileUploadService.getExcel(this.file.name, "").subscribe((responseMessage) => {
      let fileDownload = new Blob([responseMessage], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64,' });
      var fileURL = URL.createObjectURL(fileDownload);
      window.open(fileURL);
    })
    // if (this.fileType == "pdf") {
    //   this.pdfService.getPdf(this.fileName, this.fileType).subscribe((responseMessage) => {
    //     let file = new Blob([responseMessage], { type: 'application/pdf' });
    //     var fileURL = URL.createObjectURL(file);
    //     window.open(fileURL);
    //   })
    // } else {
      
    // }

  }

  
}



// download(){
  //   this.fileUploadService.download().subscribe(response => {
	// 		//let blob:any = new Blob([response.blob()], { type: 'text/json; charset=utf-8' });
	// 		//const url= window.URL.createObjectURL(blob);
	// 		//window.open(url);
	// 		window.location.href = response.url;
	// 		//fileSaver.saveAs(blob, 'employees.json');
	// 	  }), error => console.log('Error downloading the file'),
  //                () => console.info('File downloaded successfully');
  // }
  