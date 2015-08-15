/*
 * Copyright (c) 2015 Spotify AB.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.spotify.heroic.metric;

import java.util.Comparator;

public interface Metric {
    public long getTimestamp();

    /**
     * A has implementation that should only take the sample value into account.
     *
     * @return The hash of the sample value.
     */
    public int hash();

    boolean valid();

    public static Comparator<Metric> comparator() {
        return comparator;
    }

    public static Metric invalid() {
        return invalid;
    }

    static final Comparator<Metric> comparator = new Comparator<Metric>() {
        @Override
        public int compare(Metric a, Metric b) {
            return Long.compare(a.getTimestamp(), b.getTimestamp());
        }
    };

    static final Metric invalid = new Metric() {
        @Override
        public long getTimestamp() {
            throw new IllegalStateException("invalid has not timestamp");
        }

        @Override
        public int hash() {
            return 0;
        }

        @Override
        public boolean valid() {
            return false;
        }
    };
}