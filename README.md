# consume_and_expose_restapi_using_spring_boot
This application consume's Homebrew’s JSON API https://formulae.brew.sh/docs/api/#get-formula-metadata-for-a-core-formula to get the formula and expose an endpoint [GET] /formula?name=
which takes a formula name as a request parameter and returns its description,
version, dependencies and generated date.

Homebrew is a package manager used for MacOS but can also be used for Linux.
Much like Maven or Gradle which downloads dependencies (basically projects)
from a central repository for your application and make them available for you to
work with. Homebrew downloads and installs software packages from its central
repository for your MacOS so that you can work with it.
