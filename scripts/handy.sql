psql -U folio_admin -h localhost okapi_modules

set search_path to diku_mod_licenses, public;

select * from license;



