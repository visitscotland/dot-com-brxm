package com.visitscotland.brxm.model;

import com.visitscotland.brxm.hippobeans.Spotlight;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

public class SpotlightModule extends Module<Spotlight> {

        private String title;
        private HippoHtml copy;
        private FlatLink cta;
        private FlatImage image;
        private String layout;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public HippoHtml getCopy() {
            return copy;
        }

        public void setCopy(HippoHtml copy) {
            this.copy = copy;
        }

        public FlatLink getCta() {
            return cta;
        }

        public void setCta(FlatLink cta) {
            this.cta = cta;
        }

        public FlatImage getImage() {
            return image;
        }

        public void setImage(FlatImage image) {
            this.image = image;
        }

        public String getLayout() {
            return layout;
        }

        public void setLayout(String layout) {
            this.layout = layout;
        }

}


