package com.dpcheck

class DataModel {
    var graphql: Graphql?=null

    class Graphql{
        var user:User?=null

        class User{
            var biography=""
            var edge_followed_by:Followers?=null
            var edge_follow:Following?=null
            var full_name=""
            var profile_pic_url_hd=""

            class Followers{
                var count:Int?=null
            }

            class Following{
                var count:Int?=null
            }
        }
    }
}