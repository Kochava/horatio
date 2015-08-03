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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.runningreds.horatio.FileRef;

public class ModelSet {

    private final Map<String, Model> models = new HashMap<String, Model>();
    private final Map<String, Type> typeMap = new HashMap<String, Type>();
    private final Map<String, Type> uncategorizedMap = new HashMap<String, Type>();
    
    private volatile FileRef modelPath;
    
    public ModelSet() {
        synchronized(typeMap) {
            Type.registerPrimitiveTypes(this);
        }
    }
    
    public FileRef getModelPath() {
        return modelPath;
    }

    public void setModelPath(FileRef modelPath) {
        this.modelPath = modelPath;
    }

    public Model createModel(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name");
        }
        synchronized(models) {
            if (models.containsKey(name)) {
                throw new ModelException("Model \"" + name + "\" already exists");
            }
            Model model = new Model(this, name); 
            models.put(name, model);
            return model;
        }
    }
    
    public boolean isModelLoaded(String name) {
        synchronized(models) {
            return models.containsKey(name);
        }
    }
    
    public Model getModel(String name) {
        synchronized(models) {
            return models.get(name);
        }
    }
    
    public boolean containsModel(String name) {
        synchronized(models) {
            return models.containsKey(name);
        }
    }
    
    public boolean isQName(String name) {
        int dot;
        if ((dot = name.indexOf('.')) < 0) {
            return false;
        }
        return models.containsKey(name.substring(0, dot));
    }
    
    public List<Model> getLoadedModels() {
        synchronized(models) {
            return new ArrayList<Model>(models.values());
        }
    }

    public void registerType(String name, Type type) throws ModelException {
        synchronized(typeMap) {
            if (typeMap.containsKey(name)) {
                throw new ModelException("Type \"" + name + "\" already registered");
            }
            if (type.isUncatgorized()) {
                uncategorizedMap.put(name, type);
            } else {
                uncategorizedMap.remove(type);
                typeMap.put(name, type);
            }
        }
    }
    
    public void hasUncategorizedTypes() {
        
    }
    
    public Type registerTypeIfNew(String name, Type type) throws ModelException {
        synchronized(typeMap) {
            Type t;
            if ((t = typeMap.get(name)) == null) {
                typeMap.put(name, t = type);
            }
            return t;
        }
    }
    
    public boolean isTypeRegistered(String name) {
        synchronized(typeMap) {
            return typeMap.containsKey(name);
        }
    }

    public Type getType(String name) {
        synchronized(typeMap) {
            return typeMap.get(name);
        }
    }
    

}
