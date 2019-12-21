package Data;

import java.io.File;

import FormLogin.Login;

public class Launch {
	public static String path = "./images";

	public static void main(String[] args) {
		File Folder = new File(path);
		
		if(!Folder.exists()) {
			try {
				Folder.mkdir();
				System.out.println("images 풀더 생성 완료");
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			System.out.println("images 풀더 확인 완료");
		}
		
		
		
		Jdbc.dbConnect("root", "apmsetup", "booksystem", "member");
		new Login();
	}

}
