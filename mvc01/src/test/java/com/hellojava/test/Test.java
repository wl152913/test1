package com.hellojava.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Test {

	public static void main(String[] args) throws IOException {
		
		File srcDir=new File("java");
		//检查此路径的文件是不是一个目录
		if(!(srcDir.exists())&&srcDir.isDirectory());
		//FilenameFilter 文件过滤器    listFiles列出全部文件
		File[]files=srcDir.listFiles(new FilenameFilter() {
			
			public boolean accept(File dir,String name) {
				//判断是不是以.Java结尾
				return name.endsWith(".java");
			}
		});
		
		System.out.println(files.length);
		File destDir = new File("jad");
		if(!destDir.exists())destDir.mkdirs();
		for(File f:files) {
			//文件名或目录名
			FileInputStream fis= new FileInputStream(f);
			String destFileName = f.getName().replaceAll("\\.java$", ".jad");
			FileOutputStream fos= new FileOutputStream(new File(destDir,destFileName));
			copy(fis,fos);
			fis.close();
			fos.close();
		}
	}
	
	private static void copy(InputStream ips,OutputStream ops) throws IOException {
		int len=0;
		byte[] buf=new byte[1024];
		//读    存在
		while((len=ips.read(buf))!=-1) {
			//写
			ops.write(buf, 0, len);
		}
	}
	
}

