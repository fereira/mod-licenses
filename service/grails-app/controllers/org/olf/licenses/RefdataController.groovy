package org.olf.licenses

import com.k_int.web.toolkit.custprops.CustomPropertyDefinition
import com.k_int.web.toolkit.refdata.GrailsDomainRefdataHelpers
import com.k_int.web.toolkit.refdata.RefdataCategory
import com.k_int.web.toolkit.rest.RestfulController
import com.k_int.web.toolkit.utils.DomainUtils
import grails.web.Controller
import groovy.util.logging.Slf4j

@Slf4j
@Controller
class RefdataController extends com.k_int.web.toolkit.RefdataController {

  RefdataController() {
    super(RefdataCategory)
  }
  
  def lookup (String domain, String property) {
    DomainUtils d
    def c = DomainUtils.resolveDomainClass(domain)?.javaClass
    def cat = c ? GrailsDomainRefdataHelpers.getCategoryString(c, property) : null
    
    // Bail if no cat.
    if (!cat) {
      render status: 404
    } else {
      forward action: "index", params: [filters: ["owner.desc==${cat}"]]
    }
  }
}