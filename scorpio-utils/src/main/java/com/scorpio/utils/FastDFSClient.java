package com.scorpio.utils;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

/**
 * 
 * @author Administrator
 *
 */
public class FastDFSClient {

    private TrackerClient trackerClient = null;
    private TrackerServer trackerServer = null;
    private StorageServer storageServer = null;
    private StorageClient1 storageClient = null;
    
    public FastDFSClient(String conf) throws Exception{
        
        if(conf.contains("classpath:")){
            conf = conf.replace("classpath:", this.getClass().getResource("/").getPath());
        }
        
        ClientGlobal.init(conf);
        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageServer = null;
        storageClient = new StorageClient1(trackerServer, storageServer);
    }
    
    /**
     * 文件上传
     * @param fileName 文件全路径
     * @param extName 文件扩展名,不包含(.)
     * @param metas 文件扩展信息
     * @return
     * @throws Exception
     */
    public String uploadFile(String fileName,String extName,NameValuePair[] metas) throws Exception {
        String result = storageClient.upload_file1(fileName, extName, metas);
        return result;
    }
    
    public String uploadFile(String fileName) throws Exception {
        return uploadFile(fileName, null, null);
    }
    
    public String uploadFile(String fileName,String extName) throws Exception {
        return uploadFile(fileName, extName, null);
    }
    
    /**
     * 文件上传
     * @param fileContent 文件内容
     * @param extName 文件扩展名,不包含(.)
     * @param metas 文件扩展信息
     * @return
     * @throws Exception
     */
    public String uploadFile(byte[] fileContent,String extName,NameValuePair[] metas) throws Exception {
        String result = storageClient.upload_file1(fileContent, extName, metas);
        return result;
    }
    
    public String uploadFile(byte[] fileContent) throws Exception {
        return uploadFile(fileContent, null, null);
    }
    
    public String uploadFile(byte[] fileContent,String extName) throws Exception {
        return uploadFile(fileContent, extName, null);
    }
    
    

}
