package com.ckx.web.core.lucene.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.ckx.web.core.bean.SearchBean;
import com.ckx.web.core.lucene.LuceneService;
import com.ckx.web.persist.entity.Movie;
import com.ckx.web.persist.mapper.MovieMapper;

@Service("luceneService")
public class LuceneServiceImpl implements LuceneService
{
	private @Autowired IndexWriter		indexWriter;

	private @Autowired MovieMapper		movieMapper;

	private @Autowired IndexSearcher	indexSearcher;

	private @Autowired IKAnalyzer		analyzer;

	@Override
	public boolean createIndex()
	{
		List<Movie> list = movieMapper.listAll();
		try
		{
			Document doc = null;
			for (Movie movie : list)
			{
				doc = new Document();
				Field id = new StringField("id", movie.getId() + "", Field.Store.YES);
				Field name = new StringField("name", movie.getName() + "", Field.Store.YES);
				Field pub_date = new StringField("pub_date", movie.getPubDate() + "", Field.Store.YES);
				doc.add(id);
				doc.add(name);
				doc.add(pub_date);
				indexWriter.addDocument(doc);
			}
			indexWriter.commit();
			indexWriter.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeIndex()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateIndex()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<SearchBean> search(String q) throws Exception
	{
		QueryParser parser = new QueryParser(q, analyzer);
		Query query = parser.parse(q);
		TopDocs topDocs = indexSearcher.search(query, 100);
		List<SearchBean> list = new ArrayList<SearchBean>();
		SearchBean bean = null;
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		for (int i = 0; i < scoreDocs.length; i++)
		{
			int docId = scoreDocs[i].doc;
			Document doc = indexSearcher.doc(docId);
			bean = new SearchBean();
			bean.setId(doc.get("id"));
			bean.setName(doc.get("name"));
			bean.setPubDate(doc.get("pubDate"));
			list.add(bean);
		}
		return list;
	}

	/**   
	* 返回结果并添加到List中   
	* @param scoreDocs   
	* @return   
	* @throws Exception   
	*//*
	private List<SearchBean> addHits2List(ScoreDoc[] scoreDocs) throws Exception
	{
		List<SearchBean> listBean = new ArrayList<SearchBean>();
		SearchBean bean = null;
		for (int i = 0; i < scoreDocs.length; i++)
		{
			int docId = scoreDocs[i].doc;
			Document doc = searcher.doc(docId);
			bean = new SearchBean();
			bean.setId(doc.get("id"));
			bean.setUsername(doc.get("username"));
			listBean.add(bean);
		}
		return listBean;
	}*/

}
