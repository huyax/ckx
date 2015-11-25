package com.ckx.web.core.lucene;

public interface LuceneService {

	public boolean createIndex();

	public boolean removeIndex();

	public boolean updateIndex();

	public boolean search();

}
