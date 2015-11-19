package com.ckx.lang;

public class FileUpload {
	
	private String	localName;
	private String	localPath;
	private String	remoteName;
	// 磁盘目录
	private String	diskPath;
	
	public String getLocalName() {
		return localName;
	}
	
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	
	public String getLocalPath() {
		return localPath;
	}
	
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
	
	public String getRemoteName() {
		return remoteName;
	}
	
	public void setRemoteName(String remoteName) {
		this.remoteName = remoteName;
	}
	
	public String getDiskPath() {
		return diskPath;
	}
	
	public void setDiskPath(String diskPath) {
		this.diskPath = diskPath;
	}
	
}
