package com.myretail.casestudy.json;

import com.jayway.jsonpath.JsonPath;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ProductResponseTest {
    String s = "{\n" +
            "  \"product\": {\n" +
            "    \"deep_red_labels\": {\n" +
            "      \"total_count\": 2,\n" +
            "      \"labels\": [\n" +
            "        {\n" +
            "          \"id\": \"twbl94\",\n" +
            "          \"name\": \"Movies\",\n" +
            "          \"type\": \"merchandise type\",\n" +
            "          \"priority\": 0,\n" +
            "          \"count\": 1\n" +
            "        },\n" +
            "        {\n" +
            "          \"id\": \"rv3fdu\",\n" +
            "          \"name\": \"SA\",\n" +
            "          \"type\": \"relationship type\",\n" +
            "          \"priority\": 0,\n" +
            "          \"count\": 1\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    \"available_to_promise_network\": {\n" +
            "      \"product_id\": \"13860428\",\n" +
            "      \"id_type\": \"TCIN\",\n" +
            "      \"available_to_promise_quantity\": 22.0,\n" +
            "      \"street_date\": \"2011-11-15T06:00:00.000Z\",\n" +
            "      \"availability\": \"AVAILABLE\",\n" +
            "      \"online_available_to_promise_quantity\": 22.0,\n" +
            "      \"stores_available_to_promise_quantity\": 0.0,\n" +
            "      \"availability_status\": \"IN_STOCK\",\n" +
            "      \"multichannel_options\": [],\n" +
            "      \"is_infinite_inventory\": false,\n" +
            "      \"loyalty_availability_status\": \"IN_STOCK\",\n" +
            "      \"loyalty_purchase_start_date_time\": \"1970-01-01T00:00:00.000Z\",\n" +
            "      \"is_loyalty_purchase_enabled\": false,\n" +
            "      \"is_out_of_stock_in_all_store_locations\": false,\n" +
            "      \"is_out_of_stock_in_all_online_locations\": false\n" +
            "    },\n" +
            "    \"item\": {\n" +
            "      \"tcin\": \"13860428\",\n" +
            "      \"bundle_components\": {},\n" +
            "      \"dpci\": \"058-34-0436\",\n" +
            "      \"upc\": \"025192110306\",\n" +
            "      \"product_description\": {\n" +
            "        \"title\": \"The Big Lebowski (Blu-ray)\",\n" +
            "        \"bullet_description\": [\n" +
            "          \"<B>Movie Studio:</B> Universal Studios\",\n" +
            "          \"<B>Movie Genre:</B> Comedy\",\n" +
            "          \"<B>Software Format:</B> Blu-ray\"\n" +
            "        ]\n" +
            "      },\n" +
            "      \"buy_url\": \"https://www.target.com/p/the-big-lebowski-blu-ray/-/A-13860428\",\n" +
            "      \"enrichment\": {\n" +
            "        \"images\": [\n" +
            "          {\n" +
            "            \"base_url\": \"https://target.scene7.com/is/image/Target/\",\n" +
            "            \"primary\": \"GUEST_44aeda52-8c28-4090-85f1-aef7307ee20e\",\n" +
            "            \"content_labels\": [\n" +
            "              {\n" +
            "                \"image_url\": \"GUEST_44aeda52-8c28-4090-85f1-aef7307ee20e\"\n" +
            "              }\n" +
            "            ]\n" +
            "          }\n" +
            "        ],\n" +
            "        \"sales_classification_nodes\": [\n" +
            "          {\n" +
            "            \"node_id\": \"hp0vg\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"node_id\": \"5xswx\"\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      \"return_method\": \"This item can be returned to any Target store or Target.com.\",\n" +
            "      \"handling\": {},\n" +
            "      \"recall_compliance\": {\n" +
            "        \"is_product_recalled\": false\n" +
            "      },\n" +
            "      \"tax_category\": {\n" +
            "        \"tax_class\": \"G\",\n" +
            "        \"tax_code_id\": 99999,\n" +
            "        \"tax_code\": \"99999\"\n" +
            "      },\n" +
            "      \"display_option\": {\n" +
            "        \"is_size_chart\": false\n" +
            "      },\n" +
            "      \"fulfillment\": {\n" +
            "        \"is_po_box_prohibited\": true,\n" +
            "        \"po_box_prohibited_message\": \"We regret that this item cannot be shipped to PO Boxes.\",\n" +
            "        \"box_percent_filled_by_volume\": 0.27,\n" +
            "        \"box_percent_filled_by_weight\": 0.43,\n" +
            "        \"box_percent_filled_display\": 0.43\n" +
            "      },\n" +
            "      \"package_dimensions\": {\n" +
            "        \"weight\": \"0.18\",\n" +
            "        \"weight_unit_of_measure\": \"POUND\",\n" +
            "        \"width\": \"5.33\",\n" +
            "        \"depth\": \"6.65\",\n" +
            "        \"height\": \"0.46\",\n" +
            "        \"dimension_unit_of_measure\": \"INCH\"\n" +
            "      },\n" +
            "      \"environmental_segmentation\": {\n" +
            "        \"is_lead_disclosure\": false\n" +
            "      },\n" +
            "      \"manufacturer\": {},\n" +
            "      \"product_vendors\": [\n" +
            "        {\n" +
            "          \"id\": \"1984811\",\n" +
            "          \"manufacturer_style\": \"025192110306\",\n" +
            "          \"vendor_name\": \"Ingram Entertainment\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"id\": \"4667999\",\n" +
            "          \"manufacturer_style\": \"61119422\",\n" +
            "          \"vendor_name\": \"UNIVERSAL HOME VIDEO\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"id\": \"1979650\",\n" +
            "          \"manufacturer_style\": \"61119422\",\n" +
            "          \"vendor_name\": \"Universal Home Ent PFS\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"product_classification\": {\n" +
            "        \"product_type\": \"542\",\n" +
            "        \"product_type_name\": \"ELECTRONICS\",\n" +
            "        \"item_type_name\": \"Movies\",\n" +
            "        \"item_type\": {\n" +
            "          \"category_type\": \"Item Type: MMBV\",\n" +
            "          \"type\": 300752,\n" +
            "          \"name\": \"movies\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"product_brand\": {\n" +
            "        \"brand\": \"Universal Home Video\",\n" +
            "        \"manufacturer_brand\": \"Universal Home Video\",\n" +
            "        \"facet_id\": \"55zki\"\n" +
            "      },\n" +
            "      \"item_state\": \"READY_FOR_LAUNCH\",\n" +
            "      \"specifications\": [],\n" +
            "      \"attributes\": {\n" +
            "        \"gift_wrapable\": \"N\",\n" +
            "        \"has_prop65\": \"N\",\n" +
            "        \"is_hazmat\": \"N\",\n" +
            "        \"manufacturing_brand\": \"Universal Home Video\",\n" +
            "        \"max_order_qty\": 10,\n" +
            "        \"street_date\": \"2011-11-15\",\n" +
            "        \"media_format\": \"Blu-ray\",\n" +
            "        \"merch_class\": \"MOVIES\",\n" +
            "        \"merch_classid\": 58,\n" +
            "        \"merch_subclass\": 34,\n" +
            "        \"return_method\": \"This item can be returned to any Target store or Target.com.\",\n" +
            "        \"ship_to_restriction\": \"United States Minor Outlying Islands,American Samoa (see also separate entry under AS),Puerto Rico (see also separate entry under PR),Northern Mariana Islands,Virgin Islands, U.S.,APO/FPO,Guam (see also separate entry under GU)\"\n" +
            "      },\n" +
            "      \"country_of_origin\": \"US\",\n" +
            "      \"relationship_type_code\": \"Stand Alone\",\n" +
            "      \"subscription_eligible\": false,\n" +
            "      \"ribbons\": [],\n" +
            "      \"tags\": [],\n" +
            "      \"ship_to_restriction\": \"This item cannot be shipped to the following locations: United States Minor Outlying Islands, American Samoa, Puerto Rico, Northern Mariana Islands, Virgin Islands, U.S., APO/FPO, Guam\",\n" +
            "      \"estore_item_status_code\": \"A\",\n" +
            "      \"is_proposition_65\": false,\n" +
            "      \"return_policies\": {\n" +
            "        \"user\": \"Regular Guest\",\n" +
            "        \"policyDays\": \"30\",\n" +
            "        \"guestMessage\": \"This item must be returned within 30 days of the ship date. See return policy for details.\"\n" +
            "      },\n" +
            "      \"gifting_enabled\": false,\n" +
            "      \"packaging\": {\n" +
            "        \"is_retail_ticketed\": false\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";

    @Test
    public void testProductName() {
        String id = JsonPath.parse(s).read("$.product.item.tcin");
        Assert.assertEquals(id, "13860428");
    }
}