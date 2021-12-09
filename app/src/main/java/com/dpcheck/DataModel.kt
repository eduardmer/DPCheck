package com.dpcheck

class DataModel {
    var graphql: Graphql?=null
        get() = field

    public class Graphql{
        var user:User?=null
            get() = field

        class User{
            var biography:String?=null
                get() = field
            var edge_followed_by:Followers?=null
                get()=field
            var edge_follow:Following?=null
                get()=field
            var full_name:String?=null
                get()=field
            var profile_pic_url_hd:String?=null
                get()=field

            class Followers{
                var count:Int?=null
                    get()=field
            }

            class Following{
                var count:Int?=null
                    get()=field
            }
        }
    }
}