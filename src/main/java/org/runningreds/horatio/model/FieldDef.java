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

package org.runningreds.horatio.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldDef implements Named {
    
    public static final Object UNSET_VALUE = new Object();
    
    final short id;
    final String name;
    final TypeRef typeRef;
    private final boolean required;
    private final Object initialValue;
    private final Map<String, Object> annotations = Collections.synchronizedMap(new HashMap<String,Object>(2,2));
    private final List<String> docComments;

    public FieldDef(short id, String name, TypeRef typeRef, boolean required, Object initialValue,
            Map<String, Object> annotations, List<String> docComments) throws ModelException {
        this.id = id;
        this.name = name;
        this.typeRef = typeRef;
        this.required = required;
        this.initialValue = initialValue;
        if (annotations != null) {
            this.annotations.putAll(annotations);
        }
        this.docComments = docComments;
    }
    
//    public FieldDef(short id, String name, Type type) throws ModelException {
//        // NOTE: TBase default is optional (Thrift default is required)
//        this(id, name, type, false, UNSET_VALUE, (Map<String, Object>)null, (List<String>)null);
//    }

    public short getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getQName() {
        return name;
    }

    public Type getType() {
        return typeRef.getType();
    }

    public boolean isRequired() {
        return required;
    }

    public boolean hasInitialValue() {
        return initialValue != UNSET_VALUE;
    }
    
    public Object getInitialValue() {
        Object value;
        if ((value = initialValue) != UNSET_VALUE) {
            typeRef.getType().validate(value);
        }
        return value;
    }
    
    public void addAnnotation(String name, Object value) {
        annotations.put(name, value);
    }
    
    public boolean hasAnnotations() {
        return !annotations.isEmpty();
    }

    public Map<String, Object> getAnnotations() {
        return annotations;
    }
    
    public List<String> getDocComments() {
        return docComments;
    }

}
