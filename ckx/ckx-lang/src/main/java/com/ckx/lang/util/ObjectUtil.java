package com.ckx.lang.util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Random;

public class ObjectUtil {

  public static String getRandomString(long max) {
    Random random = new Random(max);
    return String.valueOf(random.nextLong());
  }

  public static String getRandomString() {
    Random random = new Random(9999999999L);
    return String.valueOf(random.nextLong());
  }

  public static long getRandomNum(long max) {
    Random random = new Random(max);
    return random.nextLong();
  }

  public static long getRandomNum() {
    Random random = new Random(9999999999L);
    return random.nextLong();
  }

  public static String getString(String st) {
    return st == null ? "" : st.trim();
  }

  public static String getString(Object o) {
    return o == null ? "" : o.toString();
  }

  public static Integer getInteger(Object o) {
    if (o == null) {
      return null;
    } else if (o instanceof Integer) {
      return (Integer) o;
    } else if (o instanceof String) {
      return Integer.valueOf((String) o);
    } else if (o instanceof Long) {
      return ((Long) o).intValue();
    } else {
      return Integer.valueOf(o.toString());
    }
  }

  public static Long getLong(Object o) {
    if (o == null) {
      return null;
    } else if (o instanceof Integer) {
      return ((Integer) o).longValue();
    } else if (o instanceof Long) {
      return (Long) o;
    } else if (o instanceof String) {
      return Long.valueOf((String) o);
    } else {
      return Long.valueOf(o.toString());
    }
  }
  
  public static Double getDouble(Object o) {
    if (o == null) {
      return null;
    } else if (o instanceof Integer) {
      return new Double((Integer) o);
    } else if (o instanceof Long) {
      return new Double((Long) o);
    } else if (o instanceof String) {
      return Double.valueOf((String) o);
    } else {
      return Double.valueOf(o.toString());
    }
  }

  public static int getInt(String st) {
    return st == null || (st.trim()).equals("") ? 0 : Integer.parseInt(st.trim());
  }

  public static int getInt(Object st) {
    return st == null ? 0 : Integer.parseInt(st.toString().trim());
  }

  public static long getLong(String st) {
    return st == null || (st.trim()).equals("") ? 0 : Long.parseLong(st.trim());
  }

  public static double getDouble(String st) {
    return st == null || (st.trim()).equals("") ? 0f : Double.parseDouble(st.trim());
  }

  public static BigDecimal getBigDecimal(Object o) {
    return o == null ? null : new BigDecimal(o.toString());
  }

  public static boolean isEmpty(String s) {
    if (s == null || "".equals(s)) {
      return true;
    }
    return false;
  }

  public static boolean isEmpty(Object[] c) {
    if (c == null || c.length == 0) {
      return true;
    }
    return false;
  }

  @SuppressWarnings("unchecked")
  public static boolean isEmpty(Collection c) {
    if (c == null || c.size() == 0) {
      return true;
    }
    return false;
  }

  @SuppressWarnings("unchecked")
  public static boolean isEmpty(Map map) {
    if (map == null || map.size() == 0) {
      return true;
    }
    return false;
  }

  public static boolean isNotEmpty(String s) {
    if (s == null || "".equals(s)) {
      return false;
    }
    return true;
  }

  public static boolean isNotEmpty(Object[] c) {
    if (c == null || c.length == 0) {
      return false;
    }
    return true;
  }

  @SuppressWarnings("unchecked")
  public static boolean isNotEmpty(Collection c) {
    if (c == null || c.size() == 0) {
      return false;
    }
    return true;
  }

  @SuppressWarnings("unchecked")
  public static boolean isNotEmpty(Map map) {
    if (map == null || map.size() == 0) {
      return false;
    }
    return true;
  }

  @SuppressWarnings("unchecked")
  public static boolean isEmpty(Object o) {
    if (o == null) {
      return true;
    } else if (o instanceof String) {
      return isEmpty((String) o);
    } else if (o instanceof Object[]) {
      return isEmpty((Object[]) o);
    } else if (o instanceof Collection) {
      return isEmpty((Collection) o);
    } else if (o instanceof Map) {
      return isEmpty((Map) o);
    }
    return false;
  }

  @SuppressWarnings("unchecked")
  public static boolean isNotEmpty(Object o) {
    if (o == null) {
      return false;
    } else if (o instanceof String) {
      return isNotEmpty((String) o);
    } else if (o instanceof Object[]) {
      return isNotEmpty((Object[]) o);
    } else if (o instanceof Collection) {
      return isNotEmpty((Collection) o);
    } else if (o instanceof Map) {
      return isNotEmpty((Map) o);
    }
    return true;
  }
}
