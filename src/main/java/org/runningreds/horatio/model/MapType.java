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

import java.util.HashMap;
import java.util.Map;

public class MapType extends CompositeType {
    
    private MapType(String name, TypeRef keyType, TypeRef valueType) {
        super(name, Category.CONTAINER, keyType, valueType);
    }
    
    public Type getKeyType() {
        return getElementTypes().get(0);
    }

    public Type getValueType() {
        return getElementTypes().get(1);
    }
    
    public static final String nameForKeyValueTypes(TypeRef keyType, TypeRef valueType) {
        return "map<" + keyType.getTypeName() + "," + valueType.getTypeName() + ">";
    }
    
    public static final MapType valueOf(TypeRef keyType, TypeRef valueType, ModelSet modelSet) {
        String name = nameForKeyValueTypes(keyType, valueType);
        return (MapType)modelSet.registerTypeIfNew(name, new MapType(name, keyType, valueType));
    }

    @Override
    public Object validate(Object value) throws ModelException {
        if (value instanceof ConstDef) {
            ConstDef cdef = (ConstDef)value;
            value = cdef.getValue();
            if (cdef.getType() == this) {
                return value;
            }
        }
        if (value instanceof Map) {
            Map<Object,Object> map = new HashMap<Object,Object>(((Map<?,?>)value).size());
            Type keyType = getKeyType();
            Type valType = getValueType();
            for (Map.Entry<?,?> entry : ((Map<?,?>)value).entrySet()) {
                Object k = keyType.validate(entry.getKey());
                Object v = valType.validate(entry.getValue());
                map.put(k,v);
            }
            return map;
        } else {
            return super.validate(value); // will throw
        }
    }
    











}