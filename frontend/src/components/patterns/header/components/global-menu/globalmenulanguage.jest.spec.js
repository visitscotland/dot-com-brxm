import { shallowMount, mount } from '@vue/test-utils';
import VsGlobalMenuLanguage from './GlobalMenuLanguage';

const factoryShallowMount = (propsData) => shallowMount(VsGlobalMenuLanguage, {
    slots: {
        default: 'Button text',
    },
    propsData: {
        ...propsData,
    },
});

describe('VsGlobalMenuLanguage renders', () => {
    it('should render a dropdown', () => {
        const wrapper = factoryShallowMount();

        expect(wrapper.is('vsdropdown-stub')).toBe(true);
    });

    it('should render vsicon globe', () => {
        const wrapper = factoryShallowMount({
            languageLabel: 'Language',
        });

        expect(wrapper.find('vsicon-stub[name="globe"').exists()).toBeTruthy();
        expect(wrapper.find('.vs-global-menu__languages__label').text()).toEqual('Language');
    });

    it('should render language label', () => {
        const wrapper = factoryShallowMount({
            languageLabel: 'Language',
        });

        expect(wrapper.find('.vs-global-menu__languages__label').text()).toEqual('Language');
    });

    it('should render selected language', () => {
        const wrapper = mount(VsGlobalMenuLanguage, {
            propsData: {
                currentLocale: 'es_ES',
            },
        });

        expect(wrapper.find('.vs-global-menu__languages__selected').text()).toEqual('ES');
    });
});
