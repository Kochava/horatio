/* Generated By:JavaCC: Do not edit this line. HoratioParserConstants.java */
package org.runningreds.horatio.parser.idl;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface HoratioParserConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int SINGLE_LINE_COMMENT = 9;
  /** RegularExpression Id. */
  int MULTI_LINE_COMMENT = 10;
  /** RegularExpression Id. */
  int NAMESPACE = 12;
  /** RegularExpression Id. */
  int INCLUDE = 13;
  /** RegularExpression Id. */
  int BOOL = 14;
  /** RegularExpression Id. */
  int BYTE = 15;
  /** RegularExpression Id. */
  int I16 = 16;
  /** RegularExpression Id. */
  int I32 = 17;
  /** RegularExpression Id. */
  int I64 = 18;
  /** RegularExpression Id. */
  int DOUBLE = 19;
  /** RegularExpression Id. */
  int FLOAT = 20;
  /** RegularExpression Id. */
  int STRING = 21;
  /** RegularExpression Id. */
  int BINARY = 22;
  /** RegularExpression Id. */
  int SLIST = 23;
  /** RegularExpression Id. */
  int SENUM = 24;
  /** RegularExpression Id. */
  int MAP = 25;
  /** RegularExpression Id. */
  int LIST = 26;
  /** RegularExpression Id. */
  int SET = 27;
  /** RegularExpression Id. */
  int VOID = 28;
  /** RegularExpression Id. */
  int ONEWAY = 29;
  /** RegularExpression Id. */
  int TYPEDEF = 30;
  /** RegularExpression Id. */
  int STRUCT = 31;
  /** RegularExpression Id. */
  int UNION = 32;
  /** RegularExpression Id. */
  int INTERFACE = 33;
  /** RegularExpression Id. */
  int EXCEPTION = 34;
  /** RegularExpression Id. */
  int EXTENDS = 35;
  /** RegularExpression Id. */
  int IMPLEMENTS = 36;
  /** RegularExpression Id. */
  int THROWS = 37;
  /** RegularExpression Id. */
  int SERVICE = 38;
  /** RegularExpression Id. */
  int ENUM = 39;
  /** RegularExpression Id. */
  int CONST = 40;
  /** RegularExpression Id. */
  int REQUIRED = 41;
  /** RegularExpression Id. */
  int OPTIONAL = 42;
  /** RegularExpression Id. */
  int TRUE = 43;
  /** RegularExpression Id. */
  int FALSE = 44;
  /** RegularExpression Id. */
  int NULL = 45;
  /** RegularExpression Id. */
  int RESERVED = 46;
  /** RegularExpression Id. */
  int EXTSTR = 47;
  /** RegularExpression Id. */
  int INT_VAL = 48;
  /** RegularExpression Id. */
  int DEC_VAL = 49;
  /** RegularExpression Id. */
  int HEX_VAL = 50;
  /** RegularExpression Id. */
  int FLOAT_VAL = 51;
  /** RegularExpression Id. */
  int EXPONENT = 52;
  /** RegularExpression Id. */
  int CHAR_VAL = 53;
  /** RegularExpression Id. */
  int STRING_VAL = 54;
  /** RegularExpression Id. */
  int IDENTIFIER = 55;
  /** RegularExpression Id. */
  int LETTER = 56;
  /** RegularExpression Id. */
  int DIGIT = 57;
  /** RegularExpression Id. */
  int LPAREN = 58;
  /** RegularExpression Id. */
  int RPAREN = 59;
  /** RegularExpression Id. */
  int LBRACE = 60;
  /** RegularExpression Id. */
  int RBRACE = 61;
  /** RegularExpression Id. */
  int LBRACKET = 62;
  /** RegularExpression Id. */
  int RBRACKET = 63;
  /** RegularExpression Id. */
  int SEMICOLON = 64;
  /** RegularExpression Id. */
  int COMMA = 65;
  /** RegularExpression Id. */
  int DOT = 66;
  /** RegularExpression Id. */
  int ASSIGN = 67;
  /** RegularExpression Id. */
  int LT = 68;
  /** RegularExpression Id. */
  int BANG = 69;
  /** RegularExpression Id. */
  int TILDE = 70;
  /** RegularExpression Id. */
  int HOOK = 71;
  /** RegularExpression Id. */
  int COLON = 72;
  /** RegularExpression Id. */
  int EQ = 73;
  /** RegularExpression Id. */
  int LE = 74;
  /** RegularExpression Id. */
  int GE = 75;
  /** RegularExpression Id. */
  int NE = 76;
  /** RegularExpression Id. */
  int SC_OR = 77;
  /** RegularExpression Id. */
  int SC_AND = 78;
  /** RegularExpression Id. */
  int INCR = 79;
  /** RegularExpression Id. */
  int DECR = 80;
  /** RegularExpression Id. */
  int PLUS = 81;
  /** RegularExpression Id. */
  int MINUS = 82;
  /** RegularExpression Id. */
  int STAR = 83;
  /** RegularExpression Id. */
  int SLASH = 84;
  /** RegularExpression Id. */
  int BIT_AND = 85;
  /** RegularExpression Id. */
  int BIT_OR = 86;
  /** RegularExpression Id. */
  int XOR = 87;
  /** RegularExpression Id. */
  int REM = 88;
  /** RegularExpression Id. */
  int LSHIFT = 89;
  /** RegularExpression Id. */
  int PLUSASSIGN = 90;
  /** RegularExpression Id. */
  int MINUSASSIGN = 91;
  /** RegularExpression Id. */
  int STARASSIGN = 92;
  /** RegularExpression Id. */
  int SLASHASSIGN = 93;
  /** RegularExpression Id. */
  int ANDASSIGN = 94;
  /** RegularExpression Id. */
  int ORASSIGN = 95;
  /** RegularExpression Id. */
  int XORASSIGN = 96;
  /** RegularExpression Id. */
  int REMASSIGN = 97;
  /** RegularExpression Id. */
  int LSHIFTASSIGN = 98;
  /** RegularExpression Id. */
  int RSIGNEDSHIFTASSIGN = 99;
  /** RegularExpression Id. */
  int RUNSIGNEDSHIFTASSIGN = 100;
  /** RegularExpression Id. */
  int ELLIPSIS = 101;

  /** Lexical state. */
  int DEFAULT = 0;
  /** Lexical state. */
  int IN_SINGLE_LINE_COMMENT = 1;
  /** Lexical state. */
  int IN_MULTI_LINE_COMMENT = 2;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "\"\\f\"",
    "\"//\"",
    "\"#\"",
    "\"/*\"",
    "<SINGLE_LINE_COMMENT>",
    "\"*/\"",
    "<token of kind 11>",
    "\"namespace\"",
    "\"include\"",
    "\"bool\"",
    "\"byte\"",
    "\"i16\"",
    "\"i32\"",
    "\"i64\"",
    "\"double\"",
    "\"float\"",
    "\"string\"",
    "\"binary\"",
    "\"slist\"",
    "\"senum\"",
    "\"map\"",
    "\"list\"",
    "\"set\"",
    "\"void\"",
    "<ONEWAY>",
    "\"typedef\"",
    "\"struct\"",
    "\"union\"",
    "\"interface\"",
    "\"exception\"",
    "\"extends\"",
    "\"implements\"",
    "\"throws\"",
    "\"service\"",
    "\"enum\"",
    "\"const\"",
    "\"required\"",
    "\"optional\"",
    "\"true\"",
    "\"false\"",
    "\"null\"",
    "<RESERVED>",
    "\"#:ExtendStruct\"",
    "<INT_VAL>",
    "<DEC_VAL>",
    "<HEX_VAL>",
    "<FLOAT_VAL>",
    "<EXPONENT>",
    "<CHAR_VAL>",
    "<STRING_VAL>",
    "<IDENTIFIER>",
    "<LETTER>",
    "<DIGIT>",
    "\"(\"",
    "\")\"",
    "\"{\"",
    "\"}\"",
    "\"[\"",
    "\"]\"",
    "\";\"",
    "\",\"",
    "\".\"",
    "\"=\"",
    "\"<\"",
    "\"!\"",
    "\"~\"",
    "\"?\"",
    "\":\"",
    "\"==\"",
    "\"<=\"",
    "\">=\"",
    "\"!=\"",
    "\"||\"",
    "\"&&\"",
    "\"++\"",
    "\"--\"",
    "\"+\"",
    "\"-\"",
    "\"*\"",
    "\"/\"",
    "\"&\"",
    "\"|\"",
    "\"^\"",
    "\"%\"",
    "\"<<\"",
    "\"+=\"",
    "\"-=\"",
    "\"*=\"",
    "\"/=\"",
    "\"&=\"",
    "\"|=\"",
    "\"^=\"",
    "\"%=\"",
    "\"<<=\"",
    "\">>=\"",
    "\">>>=\"",
    "\"...\"",
    "\"i16[]\"",
    "\"i32[]\"",
    "\"i64[]\"",
    "\"float[]\"",
    "\"double[]\"",
    "\">\"",
  };

}
