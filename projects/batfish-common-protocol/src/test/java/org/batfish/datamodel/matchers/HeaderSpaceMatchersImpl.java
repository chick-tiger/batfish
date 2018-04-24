package org.batfish.datamodel.matchers;

import java.util.SortedSet;
import javax.annotation.Nonnull;
import org.batfish.datamodel.HeaderSpace;
import org.batfish.datamodel.IpProtocol;
import org.batfish.datamodel.IpWildcard;
import org.batfish.datamodel.State;
import org.batfish.datamodel.SubRange;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

public class HeaderSpaceMatchersImpl {

  static class HasDstIps extends FeatureMatcher<HeaderSpace, SortedSet<IpWildcard>> {
    HasDstIps(@Nonnull Matcher<? super SortedSet<IpWildcard>> subMatcher) {
      super(subMatcher, "A HeaderSpace with dstIps:", "dstIps");
    }

    @Override
    protected SortedSet<IpWildcard> featureValueOf(HeaderSpace actual) {
      return actual.getDstIps();
    }
  }

  static class HasIpProtocols extends FeatureMatcher<HeaderSpace, SortedSet<IpProtocol>> {
    HasIpProtocols(@Nonnull Matcher<? super SortedSet<IpProtocol>> subMatcher) {
      super(subMatcher, "A HeaderSpace with ipProtocols:", "ipProtocols");
    }

    @Override
    protected SortedSet<IpProtocol> featureValueOf(HeaderSpace actual) {
      return actual.getIpProtocols();
    }
  }

  static class HasSrcIps extends FeatureMatcher<HeaderSpace, SortedSet<IpWildcard>> {
    HasSrcIps(@Nonnull Matcher<? super SortedSet<IpWildcard>> subMatcher) {
      super(subMatcher, "A HeaderSpace with srcIps:", "srcIps");
    }

    @Override
    protected SortedSet<IpWildcard> featureValueOf(HeaderSpace actual) {
      return actual.getSrcIps();
    }
  }

  static class HasSrcOrDstPorts extends FeatureMatcher<HeaderSpace, SortedSet<SubRange>> {
    HasSrcOrDstPorts(@Nonnull Matcher<? super SortedSet<SubRange>> subMatcher) {
      super(subMatcher, "A HeaderSpace with srcOrDstPorts:", "srcOrDstPorts");
    }

    @Override
    protected SortedSet<SubRange> featureValueOf(HeaderSpace actual) {
      return actual.getSrcOrDstPorts();
    }
  }

  static class HasStates extends FeatureMatcher<HeaderSpace, SortedSet<State>> {
    HasStates(@Nonnull Matcher<? super SortedSet<State>> subMatcher) {
      super(subMatcher, "A HeaderSpace with states:", "states");
    }

    @Override
    protected SortedSet<State> featureValueOf(HeaderSpace actual) {
      return actual.getStates();
    }
  }

  private HeaderSpaceMatchersImpl() {}
}