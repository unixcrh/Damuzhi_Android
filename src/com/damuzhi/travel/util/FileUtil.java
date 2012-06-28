package com.damuzhi.travel.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.damuzhi.travel.model.constant.ConstantField;

import android.R.integer;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

public class FileUtil
{
	private static final String TAG = "FileUtil";
	private List<String> lstFile = new ArrayList<String>(); 
	private ArrayList<FileInputStream> fileInput = new ArrayList<FileInputStream>();


	public List<String> GetFiles(String Path, String Extension,boolean IsIterative)
	{
		File[] files = new File(Path).listFiles();
		for (int i = 0; i < files.length; i++)
		{
			File f = files[i];
			if (f.isFile())
			{
				if (f.getPath().substring(f.getPath().length() - Extension.length()).equals(Extension))
				{
					lstFile.add(f.getPath());
				} 
				if (!IsIterative)
					break;
			} else if (f.isDirectory() && f.getPath().indexOf("/.") == -1)
			{
				GetFiles(f.getPath(), Extension, IsIterative);
			}
		}
		return lstFile;
	}

	/*
	 * 
	 * public List<String> GetFiles(String Path, String type,String Extension, boolean IsIterative) { File[] files = new File(Path).listFiles(); for (int i = 0; i < files.length; i++) { File f = files[i]; if (f.isFile()) { String fileExtension = f.getPath().substring(f.getPath().length() - Extension.length()); String fileType = f.getPath().substring(f.getPath().lastIndexOf("/")+1,f.getPath().lastIndexOf(".")); if (fileExtension.equals(Extension)&&fileType.contains(type)) { lstFile.add(f.getPath()); } //�ж���չ�� if (!IsIterative) break; } else if (f.isDirectory() && f.getPath().indexOf("/.") == -1) { GetFiles(f.getPath(), Extension, IsIterative); } } return lstFile; }
	 */

	
	public ArrayList<FileInputStream> getFileInputStreams(String Path,String type, String Extension, boolean IsIterative) 
	{

		File[] files = new File(Path).listFiles();
		if(files == null ||files.length == 0)
			return null;		
		
		for (int i = 0; i < files.length; i++)
		{
			File f = files[i];
			
			if (f.isFile())
			{
				String fileExtension = f.getPath().substring(f.getPath().length() - Extension.length());
				String fileType = f.getPath().substring(f.getPath().lastIndexOf("/") + 1,f.getPath().lastIndexOf("."));
				
				if (fileExtension.equals(Extension) && fileType.contains(type))
				{
					FileInputStream fileInputStream = null;
					try
					{
						fileInputStream = new FileInputStream(new File(f.getPath()));
						fileInput.add(fileInputStream);
						//fileInputStream.close();
						//fileInputStream = null;
					} catch (Exception e)
					{					
						Log.e(TAG, "<getFileInputStreams> but catch exception "+e.toString(),e);
					}
					/*finally
					{
						try
						{
							if(fileInputStream != null)
							{
								fileInputStream.close();
							}							
						} catch (IOException e)
						{
						}
					}
					*/
				}
				if (!IsIterative)
					break;
			} else if (f.isDirectory() && f.getPath().indexOf("/.") == -1)
			{
				getFileInputStreams(f.getPath(), type, Extension, IsIterative);
			}
		}
		return fileInput;
	}

	public void updateAppFile()
	{

	}

	public static boolean copyFile(String srcFile, String targetFile)
	{
		FileInputStream fileInputStream = null;
		OutputStream myOutput = null;
		BufferedInputStream myInput = null;
		boolean result = false;
		try
		{
			if (FileUtil.checkFileIsExits(srcFile))
			{

				fileInputStream = new FileInputStream(new File(srcFile));

				myOutput = new FileOutputStream(targetFile);
				myInput = new BufferedInputStream(
						fileInputStream);
				byte[] buffer = new byte[1024];
				int length;
				while ((length = myInput.read(buffer)) != -1)
				{
					myOutput.write(buffer, 0, length);
				}
				myOutput.flush();	
				result = true;
			} else
			{
				result = false;
			}
		} catch (Exception e)
		{
			Log.e(TAG, "<copyFile> srcFile="+srcFile+", to dest file "+targetFile 
					+", but catch exception "+e.toString(), e);
			result = false;
		} finally
		{
			if (myOutput != null){
				try
				{
					myOutput.close();
				} catch (IOException e)
				{
				}
			}
			
			if (myInput != null){
				try
				{
					myInput.close();
				} catch (IOException e)
				{
				}
			}
			
			if (fileInputStream != null){
				try
				{
					fileInputStream.close();
				} catch (IOException e)
				{				
				}
			}
		}
		
		return result;
	}

	public static void copyFile(InputStream inputStream, String targetFile)
			throws IOException
	{
		OutputStream myOutput = new FileOutputStream(targetFile);
		BufferedInputStream myInput = new BufferedInputStream(inputStream);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) != -1)
		{
			myOutput.write(buffer, 0, length);
		}
		myOutput.flush();
		myInput.close();
		myOutput.close();
	}

	public boolean writeFile(String targetFile, InputStream inputStream)
	{
		boolean flag = true;

		try
		{
			OutputStream myOutput = new FileOutputStream(targetFile);
			BufferedInputStream myInput = new BufferedInputStream(inputStream);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = inputStream.read(buffer)) != -1)
			{
				myOutput.write(buffer, 0, length);
				Log.d(TAG, "file length = " + length);
			}
			myOutput.flush();
			myInput.close();
			myOutput.close();
		} catch (Exception e)
		{
			Log.e(TAG, "<writeFile> but catch exception :"+e.toString(),e);
		} 

		return flag;
	}

	
	public static boolean checkFileIsExits(String filePath)
	{
		File file = new File(filePath);
		if (file.exists())
		{
			return true;
		}
		return false;
	}

	
    
    public static boolean deleteFolder(String filePath) {  
       boolean  flag = false;  
       File file = new File(filePath);  
        if (!file.exists()) 
        {  
            return flag;  
        } else 
        {  
            if (file.isFile()) {  
                return deleteFile(filePath);  
            } else {  
                return deleteDirectory(filePath);  
            }  
        }  
    }  
	
	
   
    public static boolean deleteFile(String filePath) {  
       boolean flag = false;  
       File file = new File(filePath);  
        if (file.isFile() && file.exists()) {  
            file.delete();  
            flag = true;  
        }  
        return flag;  
    }  
    
    
   
    public static boolean deleteDirectory(String filePath) {  
        if (!filePath.endsWith(File.separator)) {  
        	filePath = filePath + File.separator;  
        }  
        File dirFile = new File(filePath);  
        if (!dirFile.exists() || !dirFile.isDirectory()) {  
            return false;  
        }  
        boolean flag = true;  
        File[] files = dirFile.listFiles();  
        for (int i = 0; i < files.length; i++) {  
            if (files[i].isFile()) {  
                flag = deleteFile(files[i].getAbsolutePath());  
                if (!flag) break;  
            }   
            else {  
                flag = deleteDirectory(files[i].getAbsolutePath());  
                if (!flag) break;  
            }  
        }  
        if (!flag) return false;   
        if (dirFile.delete()) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
    
    
    public static Map<Integer, Integer> getFiles(String Path)
	{
    	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		File[] files = new File(Path).listFiles();
		if(files!=null && files.length>0)
		{
			for (int i = 0; i < files.length; i++)
			{
				File f = files[i];
				if (!f.isFile())
				{
					int cityId = Integer.parseInt(f.getName());
					map.put(cityId,cityId);
				} 
			}
		}		
		return map;
	}
    
    
	
	public static int freeSpaceOnSd()
	{
		StatFs stat = new StatFs(Environment.getExternalStorageDirectory()
				.getPath());
		double sdFreeMB = ((double) stat.getAvailableBlocks() * (double) stat
				.getBlockSize()) / 1024;
		return (int) sdFreeMB;
	}
}
