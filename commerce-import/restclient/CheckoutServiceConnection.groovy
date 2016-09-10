/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 */

package restclient

import groovy.json.JsonBuilder

class CheckoutServiceConnection {


    def servicePath = '/hybris/checkout/v1'

    def connection = null
    def tenant = null
    def accessToken = null

    CheckoutServiceConnection(baseurl, tenant, accessToken) {
        this.connection = new RestServiceConnection(baseurl, servicePath)
        this.accessToken = accessToken
        this.tenant = tenant
    }

    def orderCart(cartId, customerEmail) {
        def data = ["cartId"  : cartId,
        			"payment" : ["paymentId": "stripe"],
                    "customer": customerEmail]
        def dataJson = (new JsonBuilder(data)).toString()
        println "------------- CheckoutServiceConnection ---------"
        println dataJson
        println "-------------"
        connection.POST(this.tenant + '/checkouts/order', dataJson, "data for ${customerEmail}", headers())
    }

    def headers() {
        def httpHeaders = ["Authorization": "Bearer ${accessToken}"]
        return httpHeaders
    }


}
