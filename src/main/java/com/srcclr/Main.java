package com.srcclr;

import org.apache.commons.fileupload.MultipartStream;
import org.apache.xml.security.signature.XMLSignatureInput;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.util.UriUtils;
import org.yaml.snakeyaml.*;
import java.util.*;


import java.io.ByteArrayInputStream;

public class Main {

  public static void main(String[] args) throws Exception {
    String candidate = args[0];
    String hashed = BCrypt.hashpw(candidate, BCrypt.gensalt(12));

    BCrypt.checkpw(candidate, hashed);

    filterXMLSignature();
    snakes();

    // Update Advisor: changed in the upgrade from Spring Web 3.1.1.RELEASE to 3.2.15.RELEASE
    UriUtils.encodeFragment("", "");
  }

  private static void filterXMLSignature() {
    byte[] bytes = new byte[256];

    new MultipartStream(new ByteArrayInputStream(bytes), bytes);

    new XMLSignatureInput(bytes).addNodeFilter(null);
  }

  private static void snakes() {
    String data = "a: &a [\"lol\",\"lol\",\"lol\",\"lol\",\"lol\",\"lol\",\"lol\",\"lol\",\"lol\"]\n" +
            "b: &b [*a,*a,*a,*a,*a,*a,*a,*a,*a]\n" +
            "c: &c [*b,*b,*b,*b,*b,*b,*b,*b,*b]\n" +
            "d: &d [*c,*c,*c,*c,*c,*c,*c,*c,*c]\n" +
            "e: &e [*d,*d,*d,*d,*d,*d,*d,*d,*d]\n" +
            "f: &f [*e,*e,*e,*e,*e,*e,*e,*e,*e]\n" +
            "g: &g [*f,*f,*f,*f,*f,*f,*f,*f,*f]\n" +
            "h: &h [*g,*g,*g,*g,*g,*g,*g,*g,*g]\n" +
            "i: &i [*h,*h,*h,*h,*h,*h,*h,*h,*h]";
    Yaml yaml = new Yaml();
    Map map = (Map) yaml.load(data); //load yaml

  }

}
