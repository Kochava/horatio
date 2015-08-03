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
package org.runningreds.horatio;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class FileRef {

    final File file;
    final URL url;

    public FileRef(File file) {
        this.file = file;
        this.url = null;
    }

    public FileRef(URL url) {
        this.url = url;
        this.file = null;
    }

    public boolean isFile() {
        return file != null;
    }

    public boolean isUrl() {
        return url != null;
    }
    
    public boolean isEmpty() {
        return file == null && url == null;
    }

    public File getFile() {
        return file;
    }

    public URL getUrl() {
        return url;
    }
    
    public InputStream getInputStream() {
        try {
            if (file != null) {
                return new FileInputStream(file);
            } else if (url != null) {
                return url.openStream();
            } else {
                throw new IllegalStateException();
            }
        } catch (Exception e) {
            throw new GenspecException(e);
        }
        
    }
    
    public FileRef getChildRef(String filename) {
        if (file != null) {
            return new FileRef(new File(file, filename));
        } else if (url != null) {
            try {
                return new FileRef(new URL(url.toString() + "/" + filename));
            } catch (Exception e) {
                throw new GenspecException(e);
            }
        } else {
            throw new IllegalStateException();
        }
    }
    
}
