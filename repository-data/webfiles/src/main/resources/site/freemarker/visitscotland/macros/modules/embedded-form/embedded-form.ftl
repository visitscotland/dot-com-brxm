<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-form.ftl">

<#-- Reference: https://developers.marketo.com/javascript-api/forms/ -->
<@hst.headContribution category="htmlBodyEndScripts">
    <script src="//e.visitscotland.com/js/forms2/js/forms2.min.js"></script>
</@hst.headContribution>
<@hst.headContribution category="htmlBodyEndScripts">
    <script src='https://www.google.com/recaptcha/api.js'></script>
</@hst.headContribution> 

<#macro embeddedForm>
    <vs-form>
    </vs-form>
    <#--  <section class="vs-embedded-form">
        <vs-container>
            <vs-row>
                <vs-col cols="12">
                    <p>Form ID ${marketoId}</p>

                    <vs-form form-id="${marketoId}" munchkinId="${label("channel", "marketo.munchkinId")}">
                        <template v:slot-success-text>
                            <p>Well done, the form was submitted</p>
                        </template>
                    </vs-form>
                </vs-col>
            </vs-row>
        </vs-container>
    </section>  -->
</#macro>
