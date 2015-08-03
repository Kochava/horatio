/* Generated By:JavaCC: Do not edit this line. GenspecParser.java */
package org.runningreds.horatio.parser.genspec;

import java.io.*;
import java.util.*;

import org.runningreds.horatio.parser.*;

public class GenspecParser implements GenspecParserConstants {
    // these are mainly used to give better error positions
    Token nameToken, valueToken, typeToken;
    public String name;

    public GenspecParser(String name, Reader reader) {
        this(reader);
        this.name = name;
    }

    public GenspecParser(String name, InputStream stream) {
        this(stream);
        this.name = name;
    }

    public GenspecParser(String name, InputStream stream, String encoding) {
        this(stream, encoding);
        this.name = name;
    }

    static String tokenLoc(Token t) {
        if (t == null) {
            return "";
        } else {
            return " at line " + t.beginLine + ", column " + t.beginColumn;
        }
    }

    static void p(Object o) {
      System.out.println(o);
    }

  final public Map<String,Object> Genspec() throws ParseException {
   Map<String,Object> genspec;
    genspec = MapLiteral();
    jj_consume_token(0);
        {if (true) return genspec;}
    throw new Error("Missing return statement in function");
  }

  final public Object Value() throws ParseException {
   Object v;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT_VAL:
      v = LongLiteral();
      break;
    case FLOAT_VAL:
      v = DoubleLiteral();
      break;
    case TRUE:
    case FALSE:
      v = BoolLiteral();
      break;
    case STRING_VAL:
      v = StringLiteral();
      break;
    case NULL:
      v = NullLiteral();
      break;
    case LBRACKET:
      v = ListLiteral();
      break;
    case LBRACE:
      v = MapLiteral();
      break;
    case IDENTIFIER:
      v = Name();
      break;
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return v;}
    throw new Error("Missing return statement in function");
  }

  final public List<Object> ListLiteral() throws ParseException {
   List<Object> list = new ArrayList<Object>();
   Object v;
    valueToken = jj_consume_token(LBRACKET);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case TRUE:
    case FALSE:
    case NULL:
    case INT_VAL:
    case FLOAT_VAL:
    case STRING_VAL:
    case IDENTIFIER:
    case LBRACE:
    case LBRACKET:
      v = Value();
          list.add(v);
      label_1:
      while (true) {
        if (jj_2_1(2)) {
          ;
        } else {
          break label_1;
        }
        jj_consume_token(COMMA);
        v = Value();
             list.add(v);
      }
      break;
    default:
      jj_la1[1] = jj_gen;
      ;
    }
    if (jj_2_2(2)) {
      jj_consume_token(COMMA);
      jj_consume_token(RBRACKET);
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case RBRACKET:
        jj_consume_token(RBRACKET);
        break;
      default:
        jj_la1[2] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
      {if (true) return list;}
    throw new Error("Missing return statement in function");
  }

  final public Map<String,Object> MapLiteral() throws ParseException {
    Map<String,Object> map = new HashMap<String,Object>();
    Token t = null;
    String k = null;
    Object v;
    valueToken = jj_consume_token(LBRACE);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case STRING_VAL:
    case IDENTIFIER:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IDENTIFIER:
        t = jj_consume_token(IDENTIFIER);
        break;
      case STRING_VAL:
        k = StringLiteral();
        break;
      default:
        jj_la1[3] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      jj_consume_token(COLON);
      v = Value();
                if (t != null) {
                    k = t.image;
                    t = null;
                }
                map.put(k,v);
      label_2:
      while (true) {
        if (jj_2_3(2)) {
          ;
        } else {
          break label_2;
        }
        jj_consume_token(COMMA);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case IDENTIFIER:
          t = jj_consume_token(IDENTIFIER);
          break;
        case STRING_VAL:
          k = StringLiteral();
          break;
        default:
          jj_la1[4] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        jj_consume_token(COLON);
        v = Value();
                  if (t != null) {
                      k = t.image;
                      t = null;
                  }
                  map.put(k,v);
      }
      break;
    default:
      jj_la1[5] = jj_gen;
      ;
    }
    if (jj_2_4(2)) {
      jj_consume_token(COMMA);
      jj_consume_token(RBRACE);
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case RBRACE:
        jj_consume_token(RBRACE);
        break;
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
     {if (true) return map;}
    throw new Error("Missing return statement in function");
  }

  final public String Name() throws ParseException {
    Token seg;
    StringBuilder name = new  StringBuilder(32);
    nameToken = null;
    label_3:
    while (true) {
      if (jj_2_5(2)) {
        ;
      } else {
        break label_3;
      }
      seg = jj_consume_token(IDENTIFIER);
      jj_consume_token(31);
        name.append(seg.image).append('.');
        if (nameToken==null) nameToken=seg;
    }
    seg = jj_consume_token(IDENTIFIER);
        if (nameToken==null)nameToken=seg;
        {if (true) return name.length() == 0 ? seg.image : name.append(seg.image).toString();}
    throw new Error("Missing return statement in function");
  }

  final public Long LongLiteral() throws ParseException {
    valueToken = jj_consume_token(INT_VAL);
      try {
         {if (true) return ParseUtil.parseLongLiteral(valueToken.image);}
      } catch (NumberFormatException e) {
         {if (true) throw new ParseException(e.getMessage() + tokenLoc(valueToken));}
      }
    throw new Error("Missing return statement in function");
  }

  final public Double DoubleLiteral() throws ParseException {
    valueToken = jj_consume_token(FLOAT_VAL);
      try {
         {if (true) return Double.valueOf(valueToken.image);}
      } catch (NumberFormatException e) {
         {if (true) throw new ParseException(e.getMessage() + tokenLoc(valueToken));}
      }
    throw new Error("Missing return statement in function");
  }

  final public String StringLiteral() throws ParseException {
    valueToken = jj_consume_token(STRING_VAL);
       {if (true) return ParseUtil.parseStringLiteral(valueToken.image);}
    throw new Error("Missing return statement in function");
  }

  final public Object NullLiteral() throws ParseException {
    valueToken = jj_consume_token(NULL);
      {if (true) return null;}
    throw new Error("Missing return statement in function");
  }

  final public Boolean BoolLiteral() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case TRUE:
      valueToken = jj_consume_token(TRUE);
                          {if (true) return true;}
      break;
    case FALSE:
      valueToken = jj_consume_token(FALSE);
                           {if (true) return false;}
      break;
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  private boolean jj_3R_19() {
    if (jj_scan_token(LBRACKET)) return true;
    return false;
  }

  private boolean jj_3_4() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_scan_token(RBRACE)) return true;
    return false;
  }

  private boolean jj_3_3() {
    if (jj_scan_token(COMMA)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(22)) {
    jj_scanpos = xsp;
    if (jj_3R_5()) return true;
    }
    return false;
  }

  private boolean jj_3R_14() {
    if (jj_scan_token(STRING_VAL)) return true;
    return false;
  }

  private boolean jj_3R_13() {
    if (jj_3R_21()) return true;
    return false;
  }

  private boolean jj_3R_12() {
    if (jj_3R_20()) return true;
    return false;
  }

  private boolean jj_3R_11() {
    if (jj_3R_19()) return true;
    return false;
  }

  private boolean jj_3R_10() {
    if (jj_3R_18()) return true;
    return false;
  }

  private boolean jj_3R_9() {
    if (jj_3R_14()) return true;
    return false;
  }

  private boolean jj_3R_8() {
    if (jj_3R_17()) return true;
    return false;
  }

  private boolean jj_3R_7() {
    if (jj_3R_16()) return true;
    return false;
  }

  private boolean jj_3R_6() {
    if (jj_3R_15()) return true;
    return false;
  }

  private boolean jj_3R_16() {
    if (jj_scan_token(FLOAT_VAL)) return true;
    return false;
  }

  private boolean jj_3R_4() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_6()) {
    jj_scanpos = xsp;
    if (jj_3R_7()) {
    jj_scanpos = xsp;
    if (jj_3R_8()) {
    jj_scanpos = xsp;
    if (jj_3R_9()) {
    jj_scanpos = xsp;
    if (jj_3R_10()) {
    jj_scanpos = xsp;
    if (jj_3R_11()) {
    jj_scanpos = xsp;
    if (jj_3R_12()) {
    jj_scanpos = xsp;
    if (jj_3R_13()) return true;
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  private boolean jj_3R_20() {
    if (jj_scan_token(LBRACE)) return true;
    return false;
  }

  private boolean jj_3R_15() {
    if (jj_scan_token(INT_VAL)) return true;
    return false;
  }

  private boolean jj_3R_5() {
    if (jj_3R_14()) return true;
    return false;
  }

  private boolean jj_3R_23() {
    if (jj_scan_token(FALSE)) return true;
    return false;
  }

  private boolean jj_3R_22() {
    if (jj_scan_token(TRUE)) return true;
    return false;
  }

  private boolean jj_3R_17() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_22()) {
    jj_scanpos = xsp;
    if (jj_3R_23()) return true;
    }
    return false;
  }

  private boolean jj_3_2() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  private boolean jj_3_1() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_4()) return true;
    return false;
  }

  private boolean jj_3_5() {
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_scan_token(31)) return true;
    return false;
  }

  private boolean jj_3R_21() {
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_5()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  private boolean jj_3R_18() {
    if (jj_scan_token(NULL)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public GenspecParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[8];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0xa64f000,0xa64f000,0x10000000,0x600000,0x600000,0x600000,0x4000000,0x3000,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[5];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public GenspecParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public GenspecParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new GenspecParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public GenspecParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new GenspecParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public GenspecParser(GenspecParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(GenspecParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[32];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 8; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 32; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 5; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
