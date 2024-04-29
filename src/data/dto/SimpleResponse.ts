export class SimpleResponse {
  code: number;
  message: string | null;
  data: any;

  constructor(code: number, message: string | null, data: any) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  static withMessage(code: number, message: string) {
    return new SimpleResponse(code, message, null);
  }

  static withData(code: number, data: any) {
    return new SimpleResponse(code, null, data);
  }
}
