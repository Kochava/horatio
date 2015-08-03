/**
 *  Copyright 2011-2015 Bill Dortch / RunningReds.org
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.runningreds.horatio.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.runningreds.horatio.model.ConstDef;
import org.runningreds.horatio.model.EnumType;
import org.runningreds.horatio.model.FieldDef;
import org.runningreds.horatio.model.ListType;
import org.runningreds.horatio.model.NamespaceDef;
import org.runningreds.horatio.model.StructType;
import org.runningreds.horatio.model.Model;
import org.runningreds.horatio.model.Type;

public class JavaUtil {

    public String getInterfaces(StructType type) {
        if (type.hasInterfaces()) {
            StringBuilder sb = new StringBuilder(128);
            List<StructType> interfaces = type.getInterfaceTypes();
            for (int i = 0, max = interfaces.size() - 1; i <= max; i++) {
                sb.append(interfaces.get(i).getName());
                if (i < max) {
                    sb.append(", ");
                }
            }
            return sb.toString();
        } else {
            return "";
        }
    }
    
    public String javaClassName(StructType type) {
        NamespaceDef ns = type.getSchema().getNamespace("java");
        String name = type.getName();
        return ns == null || ns.getNamespace().isEmpty() ? name : ns.getNamespace() + "." + name;
    }
    
    void addClassName(List<String> types, Type ftype, String modelName) {
        Model ftypeModel = ftype.getSchema();
        if (!modelName.equals(ftypeModel.getName())) {
            NamespaceDef ns = ftypeModel.getNamespace("java");
            if (ns != null) {
                String packageName = ns.getNamespace();
                String qname = packageName + '.' + ftype.getName();
                if (!types.contains(qname)) {
                    types.add(qname);
                }
            }
        }
    }
    
    public List<String> getImportedTypes(StructType struct) {
        Model structModel = struct.getSchema();
        String modelName = structModel.getName();
        List<String> types = new ArrayList<String>();
        List<FieldDef> defs = new ArrayList<FieldDef>(struct.getFields());
        defs.addAll(struct.getTransientFields());
        for (FieldDef fd : defs) {
            Type ftype = fd.getType();
            while (ftype.isList()) {
                ftype = ((ListType)ftype).getElementType();
            }
            if (ftype.isStruct() || ftype.isEnum()) {
                addClassName(types, ftype, modelName);
            }
        }
        if (struct.getBaseType() != null) {
            addClassName(types, struct.getBaseType(), modelName);
        }
        if (struct.hasInterfaces()) {
            for (StructType t : struct.getInterfaceTypes()) {
                addClassName(types, t, modelName);
            }
        }
        if (!types.isEmpty()) {
            Collections.sort(types);
        }
        return types;
    }
    
    public String tbaseTypename(Type type) {
        if (type.isList()) {
            return tbaseTypename(((ListType)type).getElementType());
        } else if (type.isStruct()) {
            return "STRUCT";
        } else if (type.isString()) {
            return "STRING";
        } else if (type.isNumeric()) {
            if (type.isInteger()) {
                if (type == Type.I32) {
                    return "I32";
                } else if (type == Type.I64) {
                    return "I64";
                } else if (type == Type.I16) {
                    return "I16";
                } else if (type == Type.BYTE) {
                    return "BYTE";
                }
            } else {
                if (type == Type.FLOAT) {
                    return "FLOAT";
                } else if (type == Type.DOUBLE) {
                    return "DOUBLE";
                }
            }
        } else if (type.isBinary()) {
            return "BINARY";
        } else if (type.isBoolean()) {
            return "BOOLEAN";
        } else if (type.isEnum()) {
            return "I32";
        } else if (type.isPrimitiveArray()) {
            if (type == Type.I32_ARRAY) {
                return "I32_ARRAY";
            } else if (type == Type.I64_ARRAY) {
                return "I64_ARRAY";
            } else if (type == Type.I16_ARRAY) {
                return "I16_ARRAY";
            } else if (type == Type.FLOAT_ARRAY) {
                return "FLOAT_ARRAY";
            } else if (type == Type.DOUBLE_ARRAY) {
                return "DOUBLE_ARRAY";
            }
        }
        return "???";
    }
    
    public String jsMethod(Type type) {
        if (type.isFloatingPoint()) {
            return "Number";
        }
        return tbaseMethod(type);
    }
    
    public String tbaseMethod(Type type) {
        if (type.isList()) {
            return tbaseTypename(((ListType)type).getElementType());
        } else if (type.isStruct()) {
            return "Struct";
        } else if (type.isString()) {
            return "String";
        } else if (type.isNumeric()) {
            if (type.isInteger()) {
                if (type == Type.I32) {
                    return "I32";
                } else if (type == Type.I64) {
                    return "I64";
                } else if (type == Type.I16) {
                    return "I16";
                } else if (type == Type.BYTE) {
                    return "Byte";
                }
            } else {
                if (type == Type.FLOAT) {
                    return "Float";
                } else if (type == Type.DOUBLE) {
                    return "Double";
                }
            }
        } else if (type.isBinary()) {
            return "Binary";
        } else if (type.isBoolean()) {
            return "Boolean";
        } else if (type.isEnum()) {
            return "I32";
        } else if (type.isPrimitiveArray()) {
            if (type == Type.I32_ARRAY) {
                return "I32Array";
            } else if (type == Type.I64_ARRAY) {
                return "I64Array";
            } else if (type == Type.I16_ARRAY) {
                return "I16Array";
            } else if (type == Type.FLOAT_ARRAY) {
                return "FloatArray";
            } else if (type == Type.DOUBLE_ARRAY) {
                return "DoubleArray";
            }
        }
        return "???";
    }
    
    // FIXME:  need JSUtil class
    public String jsValue(FieldDef def) {
        if (def.hasInitialValue()) {
            Object ival = def.getInitialValue();
            if (ival instanceof EnumType.Elem) {
                return ((EnumType.Elem)ival).getValue().toString();
            }
            return ival.toString();
        }
        switch(def.getType().getCategory()) {
            case BOOLEAN:
                return "false";
            case INTEGER:
            case FLOATING_POINT:
                return "0";
            default:
                return "null";
        }
    }
    
    public String defaultEnumValue(FieldDef def) {
        Object value = def.getInitialValue();  
        if (value instanceof EnumType.Elem) {
            return value.toString();
        }
        return "null";
    }
    
    public String jsDefaultValue(FieldDef def) {
        if (def.hasInitialValue()) {
            if (def.getType().isEnum()) {
                return defaultEnumValue(def);
            }
            Object initialVal = def.getInitialValue();
            if (initialVal instanceof ConstDef) {
                initialVal = ((ConstDef)initialVal).getValue();
            }
            String value = initialVal.toString();
            return def.getType().getCategory() == Type.Category.STRING ? "'" + value + "'" : value;
        }
        switch(def.getType().getCategory()) {
            case BOOLEAN:
                return "false";
            case INTEGER:
            case FLOATING_POINT:
            case ENUM:
                return "0";
            default:
                return "null";
        }
    }
    
    public String defaultValue(FieldDef def) {
        if (def.hasInitialValue()) {
            Object initialVal = def.getInitialValue();
            if (initialVal instanceof ConstDef) {
                initialVal = ((ConstDef)initialVal).getValue();
            }
            String value = initialVal.toString();
            Type type = def.getType();
            switch(type.getCategory()) {
                case STRING:
                    value = "\"" + value + "\"";
                    break;
                case INTEGER:
                    if (type == Type.I64) {
                        value += "L";
                    }
                    break;
                case FLOATING_POINT:
                    if (type == Type.FLOAT) {
                        value += "f";
                    }
                    break;
                case ENUM:
                    if (value.startsWith(type.getSchema().getName())) {
                        value = value.substring(value.indexOf('.') + 1);
                    }
                    break;
                default:
                    break;
            }
            return value;
        }
        switch(def.getType().getCategory()) {
            case BOOLEAN:
                return "false";
            case INTEGER:
            case FLOATING_POINT:
                return "0";
            default:
                return "null";
        }
    }
    
    
    public boolean hasCtorFields(StructType structType) {
        for (FieldDef fd : structType.getCombinedFields()) {
            if (fd.getAnnotations().containsKey("ctor")) {
                return true;
            }
        }
        return false;
    }
    
    public List<FieldDef> getCtorFields(StructType structType) {
        ArrayList<FieldDef> fields = new ArrayList<FieldDef>();
        for (FieldDef fd : structType.getCombinedFields()) {
            if (fd.getAnnotations().containsKey("ctor")) {
                fields.add(fd);
            }
        }
        return fields;
    }
    
    public List<FieldDef> getInterfaceFields(StructType structType) {
        ArrayList<FieldDef> fields = new ArrayList<FieldDef>();
        HashSet<String> names = new HashSet<String>();
        for (StructType ifc : structType.getInterfaceTypes()) {
            for (FieldDef fd : ifc.getFields()) {
                if (!names.contains(fd.getName())) {
                    fields.add(fd);
                    names.add(fd.getName());
                }
            }
        }
        return fields;
    }
    
    public String tbonTypeName(Type type) {
        switch(type.getCategory()) {
            case STRING:
                return "String";
            case BINARY:
                return "Binary";
            case BOOLEAN:
                return "Boolean";
            case INTEGER: {
                if (type == Type.I32) {
                    return "Int";
                } else if (type == Type.I64) {
                    return "Long";
                } else if (type == Type.BYTE) {
                    return "Byte";
                } else if (type == Type.I16) {
                    return "Short";
                }
                break;
            }
            case FLOATING_POINT: {
                if (type == Type.DOUBLE) {
                    return "Double";
                } else if (type == Type.FLOAT) {
                    return "Float";
                }
                break;
            }
            case PRIMITIVE_ARRAY: {
                if (type == Type.FLOAT_ARRAY) {
                    return "FloatList";
                } else if (type == Type.DOUBLE_ARRAY) {
                    return "DoubleList";
                }
                break;
            }
            case STRUCT:
                return "Struct";
            default:
                break;
           
        }
        return "???";
    }
    
    






































}
