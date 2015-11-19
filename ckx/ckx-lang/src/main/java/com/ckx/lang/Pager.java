package com.ckx.lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pager {
  private Integer             begRow;                                // 起始索引
  private Integer             endRow;                                // 结束索引
  private Integer             pageNo;                                // 当前页码
  private Integer             pageSize;                              // 没有记录数
  private String              sort;                                  // order By 字段
  private String              order;                                 // 排序方式
  private List<String>        sumFields;                             // 需要进行SUM运算的列
  private List<String>        avgFields;                             // 需要进行AVG运算的列
  private String              footerNameKey;                         // footerNameKey
  private Map<String, Object> params = new HashMap<String, Object>(); // 查询参数
  private boolean             simpleQuery;                           // 是否仅仅只是查询数据，不做分页、排序、汇总等操作

  private List<?>             result;                                // 对应的当前页记录
  private List<?>             footer;                                // 数据统计记录
  private int                 totalPage;                             // 总页数
  private int                 totalRecord;                           // 总记录数

  public Pager() {
    simpleQuery = true;
  }

  public Pager(Integer pageSize, Integer pageNo) {
    this.pageSize = pageSize;
    this.pageNo = pageNo;
    if (pageSize != null && pageNo != null) {
      this.begRow = (pageNo - 1) * pageSize;
      this.endRow = pageNo * pageSize;
    }
  }

  public synchronized int addAvgField(String... fields) {
    if (avgFields == null) {
      avgFields = new ArrayList<String>();
    }
    for (String field : fields) {
      avgFields.add(field);
    }
    return avgFields.size();
  }

  public synchronized void addParam(String key, Object value) {
    params.put(key, value);
  }

  public synchronized int addSumField(String... fields) {
    if (sumFields == null) {
      sumFields = new ArrayList<String>();
    }
    for (String field : fields) {
      sumFields.add(field);
    }
    return sumFields.size();
  }

  public synchronized void delParam(String key) {
    if (params.containsKey(key)) {
      params.remove(key);
    }
  }

  public List<String> getAvgFields() {
    return avgFields;
  }

  public Integer getBegRow() {
    return begRow;
  }

  public Integer getEndRow() {
    return endRow;
  }

  public List<?> getFooter() {
    return footer;
  }

  public String getFooterNameKey() {
    return footerNameKey;
  }

  public String getOrder() {
    return order;
  }

  public Integer getPageNo() {
    return pageNo;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public Map<String, Object> getParam() {
    return getParamsMap();
  }

  public Map<String, Object> getParamsMap() {
    params.put("begRow", begRow);
    params.put("pageSize", pageSize);
    params.put("sort", sort);
    params.put("order", order);
    return params;
  }

  public List<?> getResult() {
    return result;
  }

  public String getSort() {
    return sort;
  }

  public List<String> getSumFields() {
    return sumFields;
  }

  public int getTotalPage() {
    return totalPage;
  }

  public int getTotalRecord() {
    return totalRecord;
  }

  public boolean isSimpleQuery() {
    this.simpleQuery = true;
    if (this.getPageSize() != null) {
      this.simpleQuery = false;
      return simpleQuery;
    }
    if (this.getSort() != null) {
      this.simpleQuery = false;
      return simpleQuery;
    }
    if (this.getSumFields() != null) {
      this.simpleQuery = false;
      return simpleQuery;
    }
    if (this.getAvgFields() != null) {
      this.simpleQuery = false;
      return simpleQuery;
    }
    return simpleQuery;
  }

  public void setAvgFields(List<String> avgFields) {
    this.avgFields = avgFields;
  }

  public void setBegRow(Integer begRow) {
    this.begRow = begRow;
  }

  public void setEndRow(Integer endRow) {
    this.endRow = endRow;
  }

  public void setFooter(List<?> footer) {
    this.footer = footer;
  }

  public void setFooterNameKey(String footerNameKey) {
    this.footerNameKey = footerNameKey;
  }

  public void setOrder(String order) {
    if (!order.matches("^\\w+$")) {
      throw new IllegalArgumentException("order 参数输入错误");
    }
    this.order = order;
  }

  public void setPageNo(Integer pageNo) {
    this.pageNo = pageNo;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public void setResult(List<?> result) {
    this.result = result;
  }

  public void setSimpleQuery(boolean simpleQuery) {
    this.simpleQuery = simpleQuery;
  }

  public void setSort(String sort) {
    if (!sort.matches("^\\w+$")) {
      throw new IllegalArgumentException("sort 参数输入错误");
    }
    this.sort = sort;
  }

  public void setSumFields(List<String> sumFields) {
    this.sumFields = sumFields;
  }

  public void setTotalPage(int totalPage) {
    this.totalPage = totalPage;
  }

  public void setTotalRecord(int totalRecord) {
    this.totalRecord = totalRecord;
    if (pageSize != null){
      int totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
      this.setTotalPage(totalPage);
    }
  }

  @Override
  public String toString() {
    return "Pager [pageSize=" + pageSize + ", pageNo=" + pageNo + ", totalRecord=" + totalRecord + ", totalPage="
        + totalPage + ", begRow=" + begRow + ", endRow=" + endRow + ", result=" + result + ", params=" + params + "]";
  }

}
