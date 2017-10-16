package com.fh.util.sysdata;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fh.util.sysdata.ConvertInterface;

public class JsonConverter
  implements ConvertInterface
{
  private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public Map<String, Object> destToSource(byte[] bts)
    throws Exception
  {
    return null;
  }

  public byte[] sourceToDest(Map<String, Object> map) throws Exception {
    StringBuilder sbd = new StringBuilder();
    toJsonValue(sbd, map);
    return sbd.toString().getBytes("UTF-8");
  }

  @SuppressWarnings("rawtypes")
private void toJsonValue(StringBuilder sbd, Object obj) throws Exception {
    Method m;
    Method[] arrayOfMethod1;
    if (obj == null) {
      sbd.append("null");
      return;
    }
    if (obj instanceof Throwable) {
      sbd.append("{");
      Method[] ms = obj.getClass().getMethods();
      String  name = null;
      int j = (arrayOfMethod1 = ms).length; 
      for ( int i = 0; i < j; ++i) {
    	  m = arrayOfMethod1[i];
        if ((m.getName().startsWith("get")) && 
          (m.getName().length() > 3) && 
          (!(m.getName().equals("getClass"))) && 
          (!(m.getName().equals("getCause"))) && 
          (!(m.getName().equals("getStackTrace"))) && 
          (!(m.getName().equals("getLocalizedMessage"))) && 
          (!(Void.TYPE.equals(m.getReturnType()))) && 
          (m.getParameterTypes().length == 0)) {
          name = m.getName().substring(3);
          if ((name.charAt(0) >= 'A') && (name.charAt(0) <= 'Z'))
            sbd.append((char)(name.charAt(0) + ' '));

          sbd.append(name.substring(1) + ":");
          toJsonValue(sbd, m.invoke(obj, new Object[0]));
          sbd.append(",");
        }
      }
      if (sbd.charAt(sbd.length() - 1) == ',') sbd.deleteCharAt(sbd.length() - 1);
      sbd.append("}");
      return;
    }
    if (obj instanceof String) {
      sbd.append("\"" + escape(obj.toString()) + "\"");
      return;
    }
    if (obj instanceof Date) {
      sbd.append("\"" + this.sdf.format((Date)obj) + "\"");
      return;
    }
    if (obj instanceof Number) {
      sbd.append(obj.toString());
      return;
    }
    if (obj instanceof Boolean) {
      sbd.append("false");
      return;
    }
    if (obj instanceof Object[]) {
      toJsonArrayValue(sbd, (Object[])obj);
      return;
    }
    if (obj instanceof Set) {
      toJsonArrayValue(sbd, ((Set)obj).toArray());
      return;
    }
    if (obj instanceof List) {
      toJsonArrayValue(sbd, ((List)obj).toArray());
      return;
    }
    if (obj instanceof Map) {
      sbd.append("{");
      Iterator name = ((Map)obj).keySet().iterator(); 
      while (name.hasNext()) {  
     	   Object key = name.next();
     	   sbd.append(key.toString() + ":");
     	   toJsonValue(sbd, ((Map)obj).get(key));
           sbd.append(",");
    	}  
      if (sbd.charAt(sbd.length() - 1) == ',') sbd.deleteCharAt(sbd.length() - 1);
      sbd.append("}");
      return;
    }

    sbd.append("{");
    Method[] ms = obj.getClass().getMethods();
    String name = null;
    int j = (arrayOfMethod1 = ms).length; for (int i = 0; i < j; ++i) { m = arrayOfMethod1[i];
      if ((m.getName().startsWith("get")) && 
        (m.getName().length() > 3) && 
        (!(m.getName().equals("getClass"))) && 
        (!(Void.TYPE.equals(m.getReturnType()))) && 
        (m.getParameterTypes().length == 0)) {
        name = m.getName().substring(3);
        if ((name.charAt(0) >= 'A') && (name.charAt(0) <= 'Z'))
          sbd.append((char)(name.charAt(0) + ' '));

        sbd.append(name.substring(1) + ":");
        toJsonValue(sbd, m.invoke(obj, new Object[0]));
        sbd.append(",");
      }
    }
    if (sbd.charAt(sbd.length() - 1) == ',') sbd.deleteCharAt(sbd.length() - 1);
    sbd.append("}");
  }

  private void toJsonArrayValue(StringBuilder sbd, Object[] obj) throws Exception {
    Object[] arrayOfObject;
    sbd.append("[");
    int j = (arrayOfObject = obj).length; for (int i = 0; i < j; ++i) { Object o = arrayOfObject[i];
      toJsonValue(sbd, o);
      sbd.append(",");
    }
    if (sbd.charAt(sbd.length() - 1) == ',') sbd.deleteCharAt(sbd.length() - 1);
    sbd.append("]");
  }

  private String escape(String str)
  {
    StringBuilder sbd = new StringBuilder(str);
    for (int i = sbd.length() - 1; i >= 0; --i) {
      if (sbd.charAt(i) == '"') {
        sbd.replace(i, i + 1, "\\\"");
      }
      else if (sbd.charAt(i) == '\\') {
        sbd.replace(i, i + 1, "\\\\");
      }
      else if (sbd.charAt(i) == '/') {
        sbd.replace(i, i + 1, "\\/");
      }
      else if (sbd.charAt(i) == '\b') {
        sbd.replace(i, i + 1, "\\b");
      }
      else if (sbd.charAt(i) == '\f') {
        sbd.replace(i, i + 1, "\\f");
      }
      else if (sbd.charAt(i) == '\n') {
        sbd.replace(i, i + 1, "\\n");
      }
      else if (sbd.charAt(i) == '\r') {
        sbd.replace(i, i + 1, "\\r");
      }
      else if (sbd.charAt(i) == '\t')
        sbd.replace(i, i + 1, "\\t");

    }

    return sbd.toString();
  }
}