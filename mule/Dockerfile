FROM thomas67/mule-ee:latest
COPY ./service-esb.jar /opt/mule/apps/
COPY ./wrapper.conf /opt/mule/conf/
CMD ["/opt/mule/bin/mule-start.sh"]