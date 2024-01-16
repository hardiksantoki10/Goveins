package com.goviens.android.models;

import java.util.List;

public class ModelGetAddress {
    /**
     * plus_code : {"compound_code":"C2JP+46 Gurugram, Haryana, India","global_code":"7JWVC2JP+46"}
     * results : [{"address_components":[{"long_name":"Unnamed Road","short_name":"Unnamed Road","types":["route"]},{"long_name":"Nambardar Market","short_name":"Nambardar Market","types":["neighborhood","political"]},{"long_name":"Sector 33","short_name":"Sector 33","types":["political","sublocality","sublocality_level_1"]},{"long_name":"Gurugram","short_name":"Gurugram","types":["locality","political"]},{"long_name":"Gurgaon","short_name":"Gurgaon","types":["administrative_area_level_2","political"]},{"long_name":"Haryana","short_name":"HR","types":["administrative_area_level_1","political"]},{"long_name":"India","short_name":"IN","types":["country","political"]},{"long_name":"122005","short_name":"122005","types":["postal_code"]}],"formatted_address":"Unnamed Road, Nambardar Market, Sector 33, Gurugram, Haryana 122005, India","geometry":{"bounds":{"northeast":{"lat":28.4303944,"lng":77.0356573},"southwest":{"lat":28.4302652,"lng":77.0354414}},"location":{"lat":28.4303298,"lng":77.0355494},"location_type":"GEOMETRIC_CENTER","viewport":{"northeast":{"lat":28.4316787802915,"lng":77.03689833029149},"southwest":{"lat":28.4289808197085,"lng":77.03420036970849}}},"place_id":"ChIJvYW2GnMYDTkRiE9MF-34LwU","types":["route"]},{"address_components":[{"long_name":"Nambardar Market","short_name":"Nambardar Market","types":["neighborhood","political"]},{"long_name":"Gurugram","short_name":"Gurugram","types":["locality","political"]},{"long_name":"Gurgaon","short_name":"Gurgaon","types":["administrative_area_level_2","political"]},{"long_name":"Haryana","short_name":"HR","types":["administrative_area_level_1","political"]},{"long_name":"India","short_name":"IN","types":["country","political"]},{"long_name":"122005","short_name":"122005","types":["postal_code"]}],"formatted_address":"Nambardar Market, Gurugram, Haryana 122005, India","geometry":{"bounds":{"northeast":{"lat":28.4311489,"lng":77.0372071},"southwest":{"lat":28.4287294,"lng":77.0349155}},"location":{"lat":28.4300003,"lng":77.03665649999999},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":28.4312881302915,"lng":77.03741028029151},"southwest":{"lat":28.4285901697085,"lng":77.0347123197085}}},"place_id":"ChIJuzzMCXMYDTkRgYn7VgPKnek","types":["neighborhood","political"]},{"address_components":[{"long_name":"122005","short_name":"122005","types":["postal_code"]},{"long_name":"Gurugram","short_name":"Gurugram","types":["locality","political"]},{"long_name":"Gurgaon","short_name":"Gurgaon","types":["administrative_area_level_2","political"]},{"long_name":"Haryana","short_name":"HR","types":["administrative_area_level_1","political"]},{"long_name":"India","short_name":"IN","types":["country","political"]}],"formatted_address":"Gurugram, Haryana 122005, India","geometry":{"bounds":{"northeast":{"lat":28.4412459,"lng":77.0493633},"southwest":{"lat":28.4267207,"lng":77.01852559999999}},"location":{"lat":28.4362246,"lng":77.03053899999999},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":28.4412459,"lng":77.0493633},"southwest":{"lat":28.4267207,"lng":77.01852559999999}}},"place_id":"ChIJJ-J8-ZEhDTkRPW4AHG81TDc","types":["postal_code"]},{"address_components":[{"long_name":"Sector 33","short_name":"Sector 33","types":["political","sublocality","sublocality_level_1"]},{"long_name":"Gurugram","short_name":"Gurugram","types":["locality","political"]},{"long_name":"Gurgaon","short_name":"Gurgaon","types":["administrative_area_level_2","political"]},{"long_name":"Haryana","short_name":"HR","types":["administrative_area_level_1","political"]},{"long_name":"India","short_name":"IN","types":["country","political"]}],"formatted_address":"Sector 33, Gurugram, Haryana, India","geometry":{"bounds":{"northeast":{"lat":28.448419,"lng":77.0370679},"southwest":{"lat":28.4261629,"lng":77.009942}},"location":{"lat":28.4388158,"lng":77.02766},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":28.448419,"lng":77.0370679},"southwest":{"lat":28.4261629,"lng":77.009942}}},"place_id":"ChIJ0a51eBwYDTkR9ap9UrYyIE4","types":["political","sublocality","sublocality_level_1"]},{"address_components":[{"long_name":"Gurugram","short_name":"Gurugram","types":["locality","political"]},{"long_name":"Gurgaon","short_name":"Gurgaon","types":["administrative_area_level_2","political"]},{"long_name":"Haryana","short_name":"HR","types":["administrative_area_level_1","political"]},{"long_name":"India","short_name":"IN","types":["country","political"]}],"formatted_address":"Gurugram, Haryana, India","geometry":{"bounds":{"northeast":{"lat":28.5421201,"lng":77.1226452},"southwest":{"lat":28.303795,"lng":76.85691829999999}},"location":{"lat":28.4594965,"lng":77.0266383},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":28.5421201,"lng":77.1226452},"southwest":{"lat":28.303795,"lng":76.85691829999999}}},"place_id":"ChIJWYjjgtUZDTkRHkvG5ehfzwI","types":["locality","political"]},{"address_components":[{"long_name":"Gurgaon","short_name":"Gurgaon","types":["administrative_area_level_2","political"]},{"long_name":"Haryana","short_name":"HR","types":["administrative_area_level_1","political"]},{"long_name":"India","short_name":"IN","types":["country","political"]}],"formatted_address":"Gurgaon, Haryana, India","geometry":{"bounds":{"northeast":{"lat":28.5421201,"lng":77.24825},"southwest":{"lat":28.19732,"lng":76.68023}},"location":{"lat":28.360613,"lng":76.8720961},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":28.5421201,"lng":77.24825},"southwest":{"lat":28.19732,"lng":76.68023}}},"place_id":"ChIJlYyNFyQYDTkRQzFwSRfgzY4","types":["administrative_area_level_2","political"]},{"address_components":[{"long_name":"Haryana","short_name":"HR","types":["administrative_area_level_1","political"]},{"long_name":"India","short_name":"IN","types":["country","political"]}],"formatted_address":"Haryana, India","geometry":{"bounds":{"northeast":{"lat":30.9128649,"lng":77.59544799999999},"southwest":{"lat":27.6529931,"lng":74.457616}},"location":{"lat":29.0587757,"lng":76.085601},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":30.9128649,"lng":77.59544799999999},"southwest":{"lat":27.6529931,"lng":74.457616}}},"place_id":"ChIJC0BwhguwDTkRQ8GmqrSqLnM","types":["administrative_area_level_1","political"]},{"address_components":[{"long_name":"India","short_name":"IN","types":["country","political"]}],"formatted_address":"India","geometry":{"bounds":{"northeast":{"lat":35.513327,"lng":97.39535869999999},"southwest":{"lat":6.4626999,"lng":68.1097}},"location":{"lat":20.593684,"lng":78.96288},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":35.513327,"lng":97.39535869999999},"southwest":{"lat":6.4626999,"lng":68.1097}}},"place_id":"ChIJkbeSa_BfYzARphNChaFPjNc","types":["country","political"]}]
     * status : OK
     */

    private PlusCodeBean plus_code;
    private String status;
    private List<ResultsBean> results;

    public PlusCodeBean getPlus_code() {
        return plus_code;
    }

    public void setPlus_code(PlusCodeBean plus_code) {
        this.plus_code = plus_code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class PlusCodeBean {
        /**
         * compound_code : C2JP+46 Gurugram, Haryana, India
         * global_code : 7JWVC2JP+46
         */

        private String compound_code;
        private String global_code;

        public String getCompound_code() {
            return compound_code;
        }

        public void setCompound_code(String compound_code) {
            this.compound_code = compound_code;
        }

        public String getGlobal_code() {
            return global_code;
        }

        public void setGlobal_code(String global_code) {
            this.global_code = global_code;
        }
    }

    public static class ResultsBean {
        /**
         * address_components : [{"long_name":"Unnamed Road","short_name":"Unnamed Road","types":["route"]},{"long_name":"Nambardar Market","short_name":"Nambardar Market","types":["neighborhood","political"]},{"long_name":"Sector 33","short_name":"Sector 33","types":["political","sublocality","sublocality_level_1"]},{"long_name":"Gurugram","short_name":"Gurugram","types":["locality","political"]},{"long_name":"Gurgaon","short_name":"Gurgaon","types":["administrative_area_level_2","political"]},{"long_name":"Haryana","short_name":"HR","types":["administrative_area_level_1","political"]},{"long_name":"India","short_name":"IN","types":["country","political"]},{"long_name":"122005","short_name":"122005","types":["postal_code"]}]
         * formatted_address : Unnamed Road, Nambardar Market, Sector 33, Gurugram, Haryana 122005, India
         * geometry : {"bounds":{"northeast":{"lat":28.4303944,"lng":77.0356573},"southwest":{"lat":28.4302652,"lng":77.0354414}},"location":{"lat":28.4303298,"lng":77.0355494},"location_type":"GEOMETRIC_CENTER","viewport":{"northeast":{"lat":28.4316787802915,"lng":77.03689833029149},"southwest":{"lat":28.4289808197085,"lng":77.03420036970849}}}
         * place_id : ChIJvYW2GnMYDTkRiE9MF-34LwU
         * types : ["route"]
         */

        private String formatted_address;
        private GeometryBean geometry;
        private String place_id;
        private List<AddressComponentsBean> address_components;
        private List<String> types;

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public GeometryBean getGeometry() {
            return geometry;
        }

        public void setGeometry(GeometryBean geometry) {
            this.geometry = geometry;
        }

        public String getPlace_id() {
            return place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public List<AddressComponentsBean> getAddress_components() {
            return address_components;
        }

        public void setAddress_components(List<AddressComponentsBean> address_components) {
            this.address_components = address_components;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public static class GeometryBean {
            /**
             * bounds : {"northeast":{"lat":28.4303944,"lng":77.0356573},"southwest":{"lat":28.4302652,"lng":77.0354414}}
             * location : {"lat":28.4303298,"lng":77.0355494}
             * location_type : GEOMETRIC_CENTER
             * viewport : {"northeast":{"lat":28.4316787802915,"lng":77.03689833029149},"southwest":{"lat":28.4289808197085,"lng":77.03420036970849}}
             */

            private BoundsBean bounds;
            private LocationBean location;
            private String location_type;
            private ViewportBean viewport;

            public BoundsBean getBounds() {
                return bounds;
            }

            public void setBounds(BoundsBean bounds) {
                this.bounds = bounds;
            }

            public LocationBean getLocation() {
                return location;
            }

            public void setLocation(LocationBean location) {
                this.location = location;
            }

            public String getLocation_type() {
                return location_type;
            }

            public void setLocation_type(String location_type) {
                this.location_type = location_type;
            }

            public ViewportBean getViewport() {
                return viewport;
            }

            public void setViewport(ViewportBean viewport) {
                this.viewport = viewport;
            }

            public static class BoundsBean {
                /**
                 * northeast : {"lat":28.4303944,"lng":77.0356573}
                 * southwest : {"lat":28.4302652,"lng":77.0354414}
                 */

                private NortheastBean northeast;
                private SouthwestBean southwest;

                public NortheastBean getNortheast() {
                    return northeast;
                }

                public void setNortheast(NortheastBean northeast) {
                    this.northeast = northeast;
                }

                public SouthwestBean getSouthwest() {
                    return southwest;
                }

                public void setSouthwest(SouthwestBean southwest) {
                    this.southwest = southwest;
                }

                public static class NortheastBean {
                    /**
                     * lat : 28.4303944
                     * lng : 77.0356573
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }

                public static class SouthwestBean {
                    /**
                     * lat : 28.4302652
                     * lng : 77.0354414
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }
            }

            public static class LocationBean {
                /**
                 * lat : 28.4303298
                 * lng : 77.0355494
                 */

                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }

            public static class ViewportBean {
                /**
                 * northeast : {"lat":28.4316787802915,"lng":77.03689833029149}
                 * southwest : {"lat":28.4289808197085,"lng":77.03420036970849}
                 */

                private NortheastBeanX northeast;
                private SouthwestBeanX southwest;

                public NortheastBeanX getNortheast() {
                    return northeast;
                }

                public void setNortheast(NortheastBeanX northeast) {
                    this.northeast = northeast;
                }

                public SouthwestBeanX getSouthwest() {
                    return southwest;
                }

                public void setSouthwest(SouthwestBeanX southwest) {
                    this.southwest = southwest;
                }

                public static class NortheastBeanX {
                    /**
                     * lat : 28.4316787802915
                     * lng : 77.03689833029149
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }

                public static class SouthwestBeanX {
                    /**
                     * lat : 28.4289808197085
                     * lng : 77.03420036970849
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }
            }
        }

        public static class AddressComponentsBean {
            /**
             * long_name : Unnamed Road
             * short_name : Unnamed Road
             * types : ["route"]
             */

            private String long_name;
            private String short_name;
            private List<String> types;

            public String getLong_name() {
                return long_name;
            }

            public void setLong_name(String long_name) {
                this.long_name = long_name;
            }

            public String getShort_name() {
                return short_name;
            }

            public void setShort_name(String short_name) {
                this.short_name = short_name;
            }

            public List<String> getTypes() {
                return types;
            }

            public void setTypes(List<String> types) {
                this.types = types;
            }
        }
    }
}
