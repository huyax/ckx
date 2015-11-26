package com.ckx.web.core.lucene;

import java.util.List;

import com.ckx.web.core.bean.SearchBean;
import com.ckx.web.persist.entity.Movie;

public interface LuceneService {

	public boolean createIndex() throws Exception;

	public void removeIndex(Movie movie) throws Exception;

	public void updateIndex(Movie movie) throws Exception;

	public void addIndex(Movie movie) throws Exception;

	public List<SearchBean> search(String q) throws Exception;

}
