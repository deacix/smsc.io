import {Component, Input, Injectable, Inject} from 'angular2/core';
import {TranslateService, TranslatePipe} from 'ng2-translate/ng2-translate';
import {ROUTER_DIRECTIVES} from 'angular2/router';
import {FaAngleLeft} from './../../directives/FaAngleLeft';
import {ActiveItem} from './../../directives/active';
import {NgClass} from 'angular2/common';
import {AnimateBox} from './../../directives/animate';
import {LocalStorage} from '../../../../../assets/js/angular2-localstorage/WebStorage'

@Component({
    selector: 'dashboard-item',
    templateUrl: 'app/components/sidebar/navigationitems/dashboarditem/dashboarditem.html',
    styles: [
        require('./dashboarditem.scss')
    ],
    directives: [
        ROUTER_DIRECTIVES,
        AnimateBox,
        NgClass,
        ActiveItem,
        FaAngleLeft
    ],
    pipes: [TranslatePipe]
})
@Injectable()
export class DashboardItem {
    @Input('showNav') showNav:boolean;
    @LocalStorage()
    public icnDsh:boolean;

    constructor(public translate: TranslateService) {
    }

    ngOnInit() {
    }

}