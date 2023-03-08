<a href="https://opensource.newrelic.com/oss-category/#new-relic-experimental"><picture><source media="(prefers-color-scheme: dark)" srcset="https://github.com/newrelic/opensource-website/raw/main/src/images/categories/dark/Experimental.png"><source media="(prefers-color-scheme: light)" srcset="https://github.com/newrelic/opensource-website/raw/main/src/images/categories/Experimental.png"><img alt="New Relic Open Source experimental project banner." src="https://github.com/newrelic/opensource-website/raw/main/src/images/categories/Experimental.png"></picture></a>


![GitHub forks](https://img.shields.io/github/forks/newrelic-experimental/newrelic-java-apache-axis2?style=social)
![GitHub stars](https://img.shields.io/github/stars/newrelic-experimental/newrelic-java-apache-axis2?style=social)
![GitHub watchers](https://img.shields.io/github/watchers/newrelic-experimental/newrelic-java-apache-axis2?style=social)

![GitHub all releases](https://img.shields.io/github/downloads/newrelic-experimental/newrelic-java-apache-axis2/total)
![GitHub release (latest by date)](https://img.shields.io/github/v/release/newrelic-experimental/newrelic-java-apache-axis2)
![GitHub last commit](https://img.shields.io/github/last-commit/newrelic-experimental/newrelic-java-apache-axis2)
![GitHub Release Date](https://img.shields.io/github/release-date/newrelic-experimental/newrelic-java-apache-axis2)


![GitHub issues](https://img.shields.io/github/issues/newrelic-experimental/newrelic-java-apache-axis2)
![GitHub issues closed](https://img.shields.io/github/issues-closed/newrelic-experimental/newrelic-java-apache-axis2)
![GitHub pull requests](https://img.shields.io/github/issues-pr/newrelic-experimental/newrelic-java-apache-axis2)
![GitHub pull requests closed](https://img.shields.io/github/issues-pr-closed/newrelic-experimental/newrelic-java-apache-axis2)

# New Relic Java Instrumentation for Apache Axis2

## Installation

> Clone this repository to your local disk or follow instructions for installing the latest release.

## Getting Started

Project can be imported into Eclipse or IntelliJ

Eclipse:
 All modules
 ./gradlew eclipse

Individual Module
 ./gradlew moduleName:eclipse
 e.g. ./gradlew apache-axis2:eclipse

 IntelliJ
 same command except use idea rather than eclipse


## Usage

Will start track calls through the Axis2 Framework.


## Building

> Set the environment variable NEW_RELIC_EXTENSIONS_DIR to the directory where you would like to build the extension jar(s)
>
> To build all modules
> ./gradlew clean install
>
> To build a modules
> ./gradlew moduleName:clean moduleName:install

## Testing

> Run gradle moduleName:test

## Verifying

> To verify that the module will load into the Java Agent used the verifyInstrumentation option
> see https://github.com/newrelic/newrelic-gradle-verify-instrumentation for more information.  
> gradle moduleName:verifyInstrumentation

## Support

New Relic hosts and moderates an online forum where customers can interact with New Relic employees as well as other customers to get help and share best practices. Like all official New Relic open source projects, there's a related Community topic in the New Relic Explorers Hub. You can find this project's topic/threads here:



## Contributing
We encourage your contributions to improve [project name]! Keep in mind when you submit your pull request, you'll need to sign the CLA via the click-through using CLA-Assistant. You only have to sign the CLA one time per project.
If you have any questions, or to execute our corporate CLA, required if your contribution is on behalf of a company,  please drop us an email at opensource@newrelic.com.

**A note about vulnerabilities**

As noted in our [security policy](../../security/policy), New Relic is committed to the privacy and security of our customers and their data. We believe that providing coordinated disclosure by security researchers and engaging with the security community are important means to achieve our security goals.

If you believe you have found a security vulnerability in this project or any of New Relic's products or websites, we welcome and greatly appreciate you reporting it to New Relic through [HackerOne](https://hackerone.com/newrelic).   

## License
[Project Name] is licensed under the [Apache 2.0](http://apache.org/licenses/LICENSE-2.0.txt) License.
>[If applicable: The [project name] also uses source code from third-party libraries. You can find full details on which libraries are used and the terms under which they are licensed in the third-party notices document.]
