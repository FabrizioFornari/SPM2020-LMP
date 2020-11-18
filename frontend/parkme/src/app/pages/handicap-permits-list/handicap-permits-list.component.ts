import { Component, OnInit, PipeTransform } from '@angular/core';

import { DecimalPipe } from '@angular/common';
import { FormControl } from '@angular/forms';

import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';

interface Country {
  name: string;
  area: number;
  population: number;
}

const COUNTRIES: Country[] = [
  {
    name: 'Russia',

    area: 17075200,
    population: 146989754,
  },
  {
    name: 'Canada',

    area: 9976140,
    population: 36624199,
  },
  {
    name: 'United States',

    area: 9629091,
    population: 324459463,
  },
  {
    name: 'China',
    area: 9596960,
    population: 1409517397,
  },
];

@Component({
  selector: 'app-handicap-permits-list',
  templateUrl: './handicap-permits-list.component.html',
  styleUrls: ['./handicap-permits-list.component.css'],
})
export class HandicapPermitsListComponent implements OnInit {
  countries$: Observable<Country[]>;
  filter = new FormControl('');

  constructor(private pipe: DecimalPipe) {
    this.countries$ = this.filter.valueChanges.pipe(
      startWith(''),
      map((text) => this.search(text, pipe))
    );
  }

  ngOnInit(): void {}

  search(text: string, pipe: PipeTransform): Country[] {
    return COUNTRIES.filter((country) => {
      const term = text.toLowerCase();
      return (
        country.name.toLowerCase().includes(term) ||
        pipe.transform(country.area).includes(term) ||
        pipe.transform(country.population).includes(term)
      );
    });
  }
}
